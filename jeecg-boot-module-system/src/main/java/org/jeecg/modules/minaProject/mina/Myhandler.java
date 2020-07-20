package org.jeecg.modules.minaProject.mina;

import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;

import java.util.Date;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/16 0:51
 */
class Myhandler extends IoHandlerAdapter {
    //session创建时调用的方法
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("sessionCreated");
    }
    //session打开时调用的方
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("sessionOpened");
    }
    //session关闭时调用的方法
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("sessionClosed");
    }
    //当缓存区都没有操纵的时候调用的方法
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("sessionIdle");
    }
    //连接出现异常调用的方法
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
    }
    //接收到数据时调用的方法
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        //读取数据
        String msg=(String) message;
        System.out.println("服务端接收到数据"+msg);
      /*  if (msg.equals("exit")){
        session.close();
        }*/
        //发一个时间数据给客户端
        Date data=  new Date();
        session.write(data);
    }
    //发送数据出去时调用的方法
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("messageSent");
        session.close();
    }
}
