package com.ws.subtitle.util;

import com.baidu.aip.speech.AipSpeech;
import com.ws.subtitle.constant.ProjectConstant;
import org.json.JSONObject;



public class Audio2Text {


    public static void Audio2text(String audioFilePath) {
        // 对本地语音文件进行识别
        AipSpeech client = new AipSpeech(ProjectConstant.APP_ID, ProjectConstant.API_KEY, ProjectConstant.SECRET_KEY);
        JSONObject asrRes = client.asr(audioFilePath, "wav", 16000, null);
        System.out.println(asrRes);
    }


}
