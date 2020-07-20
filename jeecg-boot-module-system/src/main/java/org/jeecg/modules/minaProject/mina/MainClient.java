package org.jeecg.modules.minaProject.mina;
import org.apache.mina.common.ConnectFuture;
import org.apache.mina.common.IoConnector;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/16 11:20
 */
//客户端
public class MainClient {
    private static String host="127.0.0.1";
    private  static int port=7080;
    public static void main(String[] args) {
        //IoSession:描述的是客户端和服务端连接，常常用入接收和发送数据。
        IoSession ioSession=null;
        //创建一个非阻塞的客户端
        IoConnector connector=new NioSocketConnector();
        //设置超时时间
        connector.setConnectTimeout(3000);
        //设置过滤器
        connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(
                //TextLineCodecFactory可以处理基于文字的信息
                new TextLineCodecFactory(
                        //Charset编码集
                        Charset.forName("UTF-8"),
                        //LineDelimiter.WINDOWS.getValue()编码器
                        LineDelimiter.WINDOWS.getValue(),
                        LineDelimiter.WINDOWS.getValue())));
        //添加自定义过滤器
        connector.getFilterChain().addLast("filter", new MyClientFilter());
        //设置handler，即指定客户器端的消息处理器，绑定逻辑处理类。
        connector.setHandler(new MyClientHandler());
        //连接到服务器
        ConnectFuture connectFuture=connector.connect(new InetSocketAddress(host,port));
        //等待连接创建完成
        connectFuture.awaitUninterruptibly();
        //会话创建后发送消息到服务器
        ioSession=connectFuture.getSession();
        ioSession.write("美丽");
        ioSession.getCloseFuture().awaitUninterruptibly();//等待连接断开
        //关闭连接
        connector.dispose();
    }
}
