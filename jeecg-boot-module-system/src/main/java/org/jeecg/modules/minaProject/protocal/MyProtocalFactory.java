package org.jeecg.modules.minaProject.protocal;

import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;


/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/20 0:07
 */
public class MyProtocalFactory implements ProtocolCodecFactory {
    private final MyProtocalEncoder encoder;
    private final MyProtocalDecoder decoder;
    public MyProtocalFactory(Charset charset) {
        encoder=new MyProtocalEncoder(charset);
        decoder=new MyProtocalDecoder(charset);
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return decoder;
    }
}
