package org.jeecg.modules.facerecognition.util;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.zgf.face_recognition.entity.BaiDuUserEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author: 渣高帆 <br/>
 * 创建时间: 2020/7/11 11:10<br/>
 * JDK 1.8
 * 类：人脸识别工具类
 * 作用：封装人脸识别及人脸库操作
 */
public class FaceRegisterUtil {
    public static void main(String[] args) {
        /*JSONObject faceList = (JSONObject)queryFaceGroup();
        faceList=faceList.getJSONObject("result");
        JSONArray jsonArray=faceList.getJSONArray("group_id_list");
        System.out.println(jsonArray.get(0));*/
    }
    /**
     * 方法作用:通过传入的人脸进行人脸库进行对比返回对比数据
     * @param imagePath 图片路径
     * }
     */
    public static Object faceContrast(String imagePath) {
        //通过BaiDuUserEntity
        AipFace client=new AipFace(BaiDuUserEntity.APP_ID,BaiDuUserEntity.API_KEY,BaiDuUserEntity.SECRET_KEY);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        //最多处理人脸的数目,默认值为1(仅检测图片中面积最大的那个人脸) 最大值10
        options.put("max_face_num", "10");
        //匹配阈值（设置阈值后，score低于此阈值的用户信息将不会返回） 最大100 最小0 默认80
        //此阈值设置得越高，检索速度将会越快，推荐使用默认阈值80
        options.put("match_threshold", "80");
        //图片质量控制 NONE: 不进行控制 LOW:较低的质量要求 NORMAL: 一般的质量要求 HIGH: 较高的质量要求 默认 NONE
        options.put("quality_control", "NORMAL");
        //活体检测控制 NONE: 不进行控制 LOW:较低的活体要求(高通过率 低攻击拒绝率) NORMAL: 一般的活体要求(平衡的攻击拒绝率, 通过率) HIGH: 较高的活体要求(高攻击拒绝率 低通过率) 默认NONE
        options.put("liveness_control", "LOW");
        //查找后返回的用户数量。返回相似度最高的几个用户，默认为1，最多返回50个。
        options.put("max_user_num", "1");
        String image = null;
        try {
            //将图片以字节流方法读取并转换为Base64格式
            image =  Base64Util.encode(FileUtil.readFileByBytes(imagePath));;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //图片类型
        //BASE64:图片的base64值，base64编码后的图片数据，编码后的图片大小不超过2M；
        //URL:图片的 URL地址( 可能由于网络等原因导致下载图片时间过长)；
        //FACE_TOKEN: 人脸图片的唯一标识，调用人脸检测接口时，会为每个人脸图片赋予一个唯一的FACE_TOKEN，同一张图片多次检测得到的FACE_TOKEN是同一个。
        String imageType = "BASE64";
        //从指定的group中进行查找 用逗号分隔，上限20个:“2020,2021”
        String groupIdList = "2020";
        // 人脸搜索 M:N 识别
        JSONObject res = client.multiSearch(image, imageType, groupIdList, options);
        System.out.println(res.toString(2));
        return res;
    }

    /**
     *方法作用:将上传的人脸照片保存至人脸库中(自动识别图片中是否包含人脸)，也可以通过用户id进行覆盖更新操作
     * @param imagePath 图片路径
     * @param userID 数据库对应的用户编号
     */
    public static Object faceRegistration(String imagePath,Integer userId){
        AipFace client=new AipFace(BaiDuUserEntity.APP_ID,BaiDuUserEntity.API_KEY,BaiDuUserEntity.SECRET_KEY);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        //用户资料，长度限制256B
        options.put("user_info", "null");
        //图片质量控制 NONE: 不进行控制 LOW:较低的质量要求 NORMAL: 一般的质量要求 HIGH: 较高的质量要求 默认 NONE
        options.put("quality_control", "NORMAL");
        //	活体检测控制 NONE: 不进行控制 LOW:较低的活体要求(高通过率 低攻击拒绝率) NORMAL: 一般的活体要求(平衡的攻击拒绝率, 通过率) HIGH: 较高的活体要求(高攻击拒绝率 低通过率) 默认NONE
        options.put("liveness_control", "LOW");
        //操作方式 APPEND: 当user_id在库中已经存在时，对此user_id重复注册时，新注册的图片默认会追加到该user_id下,REPLACE : 当对此user_id重复注册时,则会用新图替换库中该user_id下所有图片,默认使用APPEND
        options.put("action_type", "REPLACE");

        //将图片转换为BASE64编码格式
        String image = null;
        try {
            //将图片以字节流方法读取并转换为Base64格式
            image = Base64Util.encode(FileUtil.readFileByBytes(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //图片上传的格式
        String imageType = "BASE64";
        //人脸库的名字
        String groupId = "2020";
        //存入人脸库中的人脸编号,这里通过数据库的编号来确定
        String faceUserId = userId.toString();

        // 人脸注册或更新
        JSONObject res = client.addUser(image, imageType, groupId, faceUserId, options);
        System.out.println(res.toString(2));
        return res;
    }

    /**
     * 方法作用：检测上传的照片是否包含人脸
     * @param imagePath 图片路径
     * @return {
     *   "result": {
     *     "face_num": 1,检测到的图片中的人脸数量
     *     "face_list": [{ 人脸信息列表
     *       "liveness": {"livemapscore": 0.31}, 活体检测控制
     *       "angle": {	人脸旋转角度参数
     *         "roll": 19.68,  平面内旋转角[-180(逆时针), 180(顺时针)]
     *         "pitch": 20.7,	三维旋转之俯仰角度[-90(上), 90(下)]
     *         "yaw": -11.8  	三维旋转之左右旋转角[-90(左), 90(右)]
     *       },
     *       "face_token": "e79d9ac0e909f10a187f05ef6ccb74f2",人脸图片的唯一标识
     *       "location": { 人脸在图片中的位置
     *         "top": 779.31,人脸区域离上边界的距离
     *         "left": 799.5,人脸区域离左边界的距离
     *         "rotation": 17,人脸框相对于竖直方向的顺时针旋转角，[-180,180]
     *         "width": 962,人脸区域的宽度
     *         "height": 994 人脸区域的高度
     *       },
     *       "face_probability": 1,人脸置信度，范围【0~1】,代表这是一张人脸的概率，0最小、1最大
     *       "age": 22,	年龄范围当face_field包含age时返回
     *     }]
     *   },
     *   "log_id": 9915841012017,
     *   "error_msg": "SUCCESS",    //是否成功？success为成功
     *   "cached": 0,
     *   "error_code": 0,   //执行编码，0为成功!
     *   "timestamp": 1594453348
     */
    public static Object faceDetection(String imagePath) {
        AipFace client=new AipFace(BaiDuUserEntity.APP_ID,BaiDuUserEntity.API_KEY,BaiDuUserEntity.SECRET_KEY);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age");
        //最多处理人脸的数目，默认值为1，仅检测图片中面积最大的那个人脸；最大值10，检测图片中面积最大的几张人脸。
        options.put("max_face_num", "10");
        //人脸的类型 LIVE表示生活照：通常为手机、相机拍摄的人像图片、或从网络获取的人像图片等IDCARD表示身份证芯片照：二代身份证内置芯片中的人像照片 WATERMARK表示带水印证件照：一般为带水印的小图，如公安网小图 CERT表示证件照片：如拍摄的身份证、工卡、护照、学生证等证件图片 默认LIVE
        options.put("face_type", "LIVE");
        //活体检测控制 NONE: 不进行控制 LOW:较低的活体要求(高通过率 低攻击拒绝率) NORMAL: 一般的活体要求(平衡的攻击拒绝率, 通过率) HIGH: 较高的活体要求(高攻击拒绝率 低通过率) 默认NONE
        options.put("liveness_control", "LOW");
        String image = null;
        try {
            image = Base64Util.encode(FileUtil.readFileByBytes(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imageType = "BASE64";
        // 人脸检测
        JSONObject res = client.detect(image, imageType, options);
        System.out.println(res.toString(3));
        return res;
    }

    /**
     * 方法作用：删除人脸库中的人脸
     * @param faceCode 人脸的唯一识别码
     * @return
     */
    public static Object faceDeletion(Integer faceUserId,String faceCode) {
        AipFace client=new AipFace(BaiDuUserEntity.APP_ID,BaiDuUserEntity.API_KEY,BaiDuUserEntity.SECRET_KEY);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        //人脸编号
        String userId = faceUserId.toString();
        //人脸库
        String groupId = "2020";
        //人脸唯一识别码
        String faceToken = faceCode;

        // 人脸删除
        JSONObject res = client.faceDelete(userId, groupId, faceToken, options);
        System.out.println(res.toString(2));
        System.out.println(res.get("error_msg"));
        return  res;
    }

    /**
     * 方法作用：查询当前应用人脸组列表
     * @return 返回范围内的人脸组列表
     */
    public static Object queryFaceGroup() {
        AipFace client=new AipFace(BaiDuUserEntity.APP_ID,BaiDuUserEntity.API_KEY,BaiDuUserEntity.SECRET_KEY);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
       //默认值0，起始序号
        options.put("start", "0");
        //返回数量，默认值100，最大值1000
        options.put("length", "100");
        // 组列表查询
        JSONObject res = client.getGroupList(options);
        System.out.println(res.toString(2));
        return res;
    }
}
