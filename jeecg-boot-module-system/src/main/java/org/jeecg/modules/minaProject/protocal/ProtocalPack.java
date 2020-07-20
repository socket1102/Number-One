package org.jeecg.modules.minaProject.protocal;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/18 11:23
 */
//自定义协议包
public class ProtocalPack {
    //length代表整个包的长度
    private int length;
    //flag版本信息
    private byte flag;
    //content包体的内容
    private String content;
    public ProtocalPack(byte flag,String content){
        this.flag=flag;
        this.content=content;
        int len1=content==null?0:content.getBytes().length;
        //5的原因是length包文头int是四个字节，byte是一个字节。
        this.length=5+len1;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String toString(){
    StringBuffer sb=new StringBuffer();
    sb.append("length:").append(length);
    sb.append("flag:").append(flag);
    sb.append("content:").append(content);
    return  sb.toString();
    }
}
