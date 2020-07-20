package org.jeecg.modules.minaProject.protocal;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoAcceptor;
import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/20 0:22
 */
//服务端
public class MyProtocalServer {
    private static final int port=7080;

    public static void main(String[] args) throws IOException {
        IoAcceptor acceptor=new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(
                new TextLineCodecFactory(
                        Charset.forName("UTF-8"))));
        acceptor.getSessionConfig().setReadBufferSize(1024);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);
        acceptor.setHandler(new Myhandler());
        acceptor.bind(new InetSocketAddress(port));
        System.out.println("server start......");
    }
}

class Myhandler  extends IoHandlerAdapter{
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("server->sessionIdle");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("server->exceptionCaught");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        ProtocalPack pack= (ProtocalPack) message;
        System.out.println("服务端接收:"+pack);
    }
}

