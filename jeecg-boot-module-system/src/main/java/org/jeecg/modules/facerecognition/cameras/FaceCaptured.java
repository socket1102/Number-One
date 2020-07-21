package org.jeecg.modules.facerecognition.cameras;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

/**
 * 创建人: 渣高帆 <br/>
 * 创建时间: 2020/7/4 17:01<br/>
 * JDK 1.8
 * class:人脸抓拍
 * 作用:调用ip摄像头和usb摄像头以及本机摄像头进行人脸抓拍保存至本地
 */

public class FaceCaptured {
    static {
        //在使用OpenCV前必须加载Core.NATIVE_LIBRARY_NAME类,否则会报错
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public static void main(String[] args) {
       Runnable runnable=()->{
           videoFace();
       };
       runnable.run();
       System.out.println("退出程序");
    }
    /**
     * OpenCV-4.0.0 实时人脸识别
     */
    public static void videoFace() {

       //VideoCapture capture=new VideoCapture(0);
        VideoCapture capture=new VideoCapture("rtsp://admin:admin@192.168.43.1:8554/live");
        Mat image=new Mat();
        int index=0;
        if (capture.isOpened()) {
            while(true) {
                capture.read(image);
                HighGui.imshow("人脸识别扫描", getFace(image));
                index=HighGui.waitKey(1);
                if (index==27) {
                    break;
                }
            }
        }
       return;
    }

    /**
     * OpenCV-4.0.0 人脸识别
     * @date: 2019年5月7日12:16:55
     * @param image 待处理Mat图片(视频中的某一帧)
     * @return 处理后的图片
     */
    public static Mat getFace(Mat image) {
        // 1 读取OpenCV自带的人脸识别特征XML文件
        /*CascadeClassifier facebook=new CascadeClassifier("E:\\opencv\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");*/
        CascadeClassifier facebook=new CascadeClassifier("E:\\opencv\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
        // 2  特征匹配类
        MatOfRect face = new MatOfRect();
        // 3 特征匹配
        facebook.detectMultiScale(image, face);
        Rect[] rects=face.toArray();
        System.out.println("匹配到 "+rects.length+" 个人脸");
        // 4 为每张识别到的人脸画一个圈
        for (int i = 0; i < rects.length; i++) {
            Imgproc.rectangle(image,new Point(rects[i].x,rects[i].y), new Point(rects[i].x + rects[i].width,rects[i].y + rects[i].height), new Scalar(0, 255, 0));
            Imgproc.putText(image,"Face", new Point(rects[i].x, rects[i].y),Imgproc.FONT_HERSHEY_SCRIPT_SIMPLEX, 1.0, new Scalar(0, 255, 0),1,Imgproc.LINE_AA,false);
            //人脸保存
            Imgcodecs.imwrite("F://zgf.jpg", image);
        }
        return image;
    }
}