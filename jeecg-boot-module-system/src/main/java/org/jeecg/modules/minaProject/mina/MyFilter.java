package org.jeecg.modules.minaProject.mina;

import org.apache.mina.common.IoFilterAdapter;
import org.apache.mina.common.IoSession;
import org.apache.mina.common.WriteRequest;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/16 22:49
 */
//完成自定义过滤器
public class MyFilter extends IoFilterAdapter {
    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
        System.out.println("server->messageReceived");
        nextFilter.messageReceived(session,message);
    }

    @Override
    public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
        System.out.println("server->messageSent");
        nextFilter.messageSent(session,writeRequest);
    }
}
