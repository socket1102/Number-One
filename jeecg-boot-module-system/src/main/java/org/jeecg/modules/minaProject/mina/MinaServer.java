package org.jeecg.modules.minaProject.mina;
import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/15 23:43
 */

//使用telnet实现客户端
    //服务端
public class MinaServer {
    //定义端口
    static int PORT=7080;
    static IoAcceptor acceptor=null;
    public static void main(String[] args) throws IOException {
        //创建客户端连接器，它也是非阻塞的读取数据
    acceptor=new NioSocketAcceptor();
    //设置编码过滤器 主要完成协议的编码解码
    acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(
            new TextLineCodecFactory(
                    //Charset编码集
                    Charset.forName("UTF-8"),
                    //LineDelimiter.WINDOWS.getValue()编码器
                    LineDelimiter.WINDOWS.getValue(),
                    LineDelimiter.WINDOWS.getValue())));
        //添加自定义过滤器
        acceptor.getFilterChain().addLast("filter", new MyServerFilter());
        //设置缓冲区的大小
        acceptor.getSessionConfig().setReadBufferSize(1024);
        //设置多长时间进入空闲状态
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);
        //设置handler，即指定客户器端的消息处理器
        acceptor.setHandler(new Myhandler());
        //绑定端口号，第二个参数若不传递则使用本地的一个随机端口访问Server端
        //该方法是异步执行的，且可以同时连接多个服务端
        acceptor.bind(new InetSocketAddress(PORT));
        System.out.println("Server ->"+PORT);
    }
}
