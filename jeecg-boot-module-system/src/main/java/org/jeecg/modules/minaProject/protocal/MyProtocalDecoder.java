package org.jeecg.modules.minaProject.protocal;

import org.apache.mina.common.AttributeKey;
import org.apache.mina.common.IoBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/19 21:27
 */
//自定义协议-解码器
public class MyProtocalDecoder implements ProtocolDecoder {
    //解决我们的段包，通过key创建一个上下文保存上一次接收的数据
    private final AttributeKey CONTEXT=new AttributeKey(this.getClass(),"context");
    private final Charset charset;
    private int maxPackLength;

    public int getMaxPackLength() {
        return maxPackLength;
    }

    public void setMaxPackLength(int maxPackLength) {
        if(maxPackLength<0){
            throw new IllegalArgumentException("maxPackLength参数:"+maxPackLength);
        }
        this.maxPackLength = maxPackLength;
    }
    public MyProtocalDecoder(){
        this(Charset.defaultCharset());
    }
    public MyProtocalDecoder(Charset charset){
        this.charset=charset;
    }
    public Context getContext(IoSession session){
    Context context= (Context) session.getAttribute(CONTEXT);
    if(context==null){
    context=new Context();
    session.setAttribute(CONTEXT,context);
    }
    return context;
    }

    @Override
    public void decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
    final int packHeaderLength=5;
    //先获取上次的处理上下文，其中可能有未处理完的数据
    Context context=this.getContext(ioSession);
    // 先把当前buffer中的数据追加到Context的buffer当中
    context.append(ioBuffer);
    //把position指向0位置，把limit指向原来的position位置
    IoBuffer buffer=context.getBuffer();
    buffer.flip();
    //// 然后按数据包的协议进行读取
        while (buffer.remaining()>=packHeaderLength){
        buffer.mark();
        // 读取消息头部分
        int length=buffer.getInt();
        byte flag=buffer.get();
        //检查读取的包头是否正常，不正常的话清空buffer
        if (length<0||length>maxPackLength){
        buffer.reset();
        break;
        //读取正常的消息包，并写入输出流中，以便IoHandler进行处理
        }else if(length>=packHeaderLength&&length-packHeaderLength<=buffer.remaining()){
            int oldLimit=buffer.limit();
            buffer.limit(buffer.position()+length-packHeaderLength);
            String content=buffer.getString(context.getDecoder());
            buffer.limit(oldLimit);
            ProtocalPack pack=new ProtocalPack(flag,content);
            protocolDecoderOutput.write(pack);
        }else {
            // 如果消息包不完整
            // 将指针重新移动消息头的起始位置
            buffer.reset();
            break;
        }
        }
        if (buffer.hasRemaining()) {
            // 将数据移到buffer的最前面
            IoBuffer temp = IoBuffer.allocate(maxPackLength).setAutoExpand(true);
            temp.put(buffer);
            temp.flip();
            buffer.clear();
            buffer.put(temp);

        } else {// 如果数据已经处理完毕，进行清空,半包
            buffer.clear();
        }
    }

    @Override
    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }

    @Override
    //销毁
    public void dispose(IoSession ioSession) throws Exception {
    Context context= (Context) ioSession.getAttribute(CONTEXT);
    if (context!=null){
    ioSession.removeAttribute(CONTEXT);
    }
    }
    //记录上下文，因为数据触发没有规模，很可能只收到数据包的一半.所以，需要上下文拼起来才能完整的处理.
    public  class Context{
        private final CharsetDecoder decoder;
        private IoBuffer buffer;
        public Context(){
            decoder=charset.newDecoder();
            buffer=IoBuffer.allocate(80).setAutoExpand(true);
        }
        public void append(IoBuffer buffer) {
        this.getBuffer().put(buffer);
        }
        public  void rest(){
        decoder.reset();
        }
        public CharsetDecoder getDecoder() {
            return decoder;
        }

        public IoBuffer getBuffer() {
            return buffer;
        }

        public void setBuffer(IoBuffer buffer) {
            this.buffer = buffer;
        }
    }
}
