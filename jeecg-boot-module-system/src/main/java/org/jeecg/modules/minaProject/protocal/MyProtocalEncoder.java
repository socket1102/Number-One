package org.jeecg.modules.minaProject.protocal;

import org.apache.mina.common.IoBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/18 14:36
 */
//自定义协议-编码器
public class MyProtocalEncoder extends ProtocolEncoderAdapter {
    private final Charset charset;
    public MyProtocalEncoder(Charset charset){
    this.charset=charset;
    }
    @Override
    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        ProtocalPack pack=(ProtocalPack)ioSession;
        //缓冲区的大小，储存整个报文的长度
        IoBuffer buffer=IoBuffer.allocate(pack.getLength());
        //让缓冲区自动增长
        buffer.setAutoExpand(true);
        //设置包头
        buffer.putInt(pack.getLength());
        buffer.put(pack.getFlag());
        if (pack.getContent()!=null){
        //把对象发送出去
        buffer.put(pack.getContent().getBytes());
        }
        buffer.flip();
        protocolEncoderOutput.write(buffer);
    }
}
