package org.jeecg.modules.minaProject.mina;

import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/16 11:52
 */
public class MyClientHandler extends IoHandlerAdapter {
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
        System.out.println("客户端接收到数据"+msg);

    }

}
