package org.jeecg.modules.minaProject.mina;

import org.apache.mina.common.IoFilter;
import org.apache.mina.common.IoFilterAdapter;
import org.apache.mina.common.IoSession;
import org.apache.mina.common.WriteRequest;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/20 10:18
 */
public class MyServerFilter extends IoFilterAdapter {
    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
        System.out.println("myServerFilter->messageReceived");
        //传给下一个过滤器
        nextFilter.messageReceived(session,message);
    }

    @Override
    public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
        System.out.println("myServerFilter->messageSent");
        //传给下一个过滤器
        nextFilter.messageSent(session,writeRequest);
    }
}

