package com.ws.subtitle.util;

import com.ws.subtitle.constant.ProjectConstant;
import com.ws.subtitle.file.VideoFile;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;

import java.io.File;

public class AudioTaker {


    public static String takeAll(VideoFile videoFile) {
        return takeByTime(videoFile, 0L, videoFile.getDuration());
    }


    public static String takeByTime(VideoFile videoFile, Long beginTime, Long endTime) {
        Float duration = Float.valueOf(endTime - beginTime);
        String targetWaveNameWithSuffix = (ProjectConstant.initID++).toString() + ".wav";
        String targetPath = ProjectConstant.DEFAULT_FILEPATH + targetWaveNameWithSuffix;
        File source = new File(videoFile.getPath());
        System.out.println("源文件" + source.getPath() + "是否存在：" + source.exists());
        File target = new File(targetPath);
        if (target.exists() || target.isFile()) {
            System.out.println("目标文件" + target.getPath() + "是否删除:" + target.delete());
        }
        AudioAttributes audio = new AudioAttributes();


        audio.setCodec("pcm_s16le");  //音频解码器
        audio.setBitRate(16);   //音频比特率
        audio.setChannels(1);   //音频声道
        audio.setSamplingRate(16000);  //音频采样率

//        VideoAttributes video = new VideoAttributes();
//        video.setCodec("pcm");     //视频解码器
//        video.setBitRate(160000);  //视频比特率
//        video.setFrameRate(15);   //视频帧率
//        video.setSize(new VideoSize(400, 300));  //视频尺寸


        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOffset(Float.valueOf(beginTime));
        attrs.setDuration(duration);
        attrs.setFormat("wav");
        attrs.setAudioAttributes(audio);
//        attrs.setVideoAttributes(video);

        try {
            MediaEncoder.encoder.encode(source, target, attrs);
            System.out.println("新目标文件"+target.getPath()+"是否生成：" + target.exists());

        } catch (EncoderException e) {
            e.printStackTrace();
        }
        return targetPath;
    }


}
