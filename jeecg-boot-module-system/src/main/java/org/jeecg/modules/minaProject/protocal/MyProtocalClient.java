package org.jeecg.modules.minaProject.protocal;

import org.apache.mina.common.*;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/20 0:45
 */
public class MyProtocalClient{
    private final static String HOST="127.0.0.1";
    private final static int PORT=7080;
    static long counter = 0;
    final static int FIL = 100;
    static long start = 0;

    public static void main(String[] args) {
        start=System.currentTimeMillis();
        IoConnector connector=new NioSocketConnector();
        connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(
                new TextLineCodecFactory(
                        Charset.forName("UTF-8"))));
        connector.getSessionConfig().setReadBufferSize(1024);
        connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);
        connector.setHandler(new MyHandler());
        ConnectFuture connectFuture=connector.connect(new InetSocketAddress(HOST,PORT));
        connectFuture.addListener(new IoFutureListener<ConnectFuture>() {

            //@Override
            public void operationComplete(ConnectFuture connectFuture) {
                try {
                    if (connectFuture.isConnected()) {
                        IoSession session = connectFuture.getSession();
                        sendData(session);
                    } else {
                        System.out.println("连接不存在 ");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("start client ...");
    }
    public static void sendData(IoSession session) throws IOException {
        for (int i = 0; i < FIL; i++) {
            String content = "硕哥测试" + i;
            ProtocalPack pack = new ProtocalPack((byte) i, content);
            session.write(pack);
            System.out.println("客户端发送数据:" + pack);
        }
    }
}

class MyHandler extends IoHandlerAdapter{
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        if (status == IdleStatus.READER_IDLE) {
            session.close(true);
        }
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        ProtocalPack pack= (ProtocalPack) message;
        System.out.println("client->"+pack);
    }
}