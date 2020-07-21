package org.jeecg.modules.voice;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.util.Scanner;

/**
 * @ClassName Voice
 * @Author 王旭
 * @Date 2020/7/21 16:58
 * @Version 1.0
 * @Function <语音播报>
 *     九日
 */
public class Voice {
    public void speak(String args) {
        ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
        Dispatch sapo = sap.getObject();
        try {
            // 音量 0-100
            sap.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            sap.setProperty("Rate", new Variant(2));
            Scanner scan = new Scanner(System.in);
            String str = "体温过高，请前往医院检查";
            // 执行朗读
            Dispatch.call(sapo, "Speak", new Variant(args));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sapo.safeRelease();
            sap.safeRelease();
        }
    }
}
