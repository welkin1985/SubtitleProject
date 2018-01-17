package util;

import com.sun.org.apache.bcel.internal.classfile.Constant;
import constant.ProjectConstant;
import file.VideoFile;
import it.sauronsoftware.jave.*;

import java.io.File;

public class AudioTaker {


    public static String takeAll(VideoFile videoFile) {
        return takeByTime(videoFile, 0L, videoFile.getDuration());
    }


    public static String takeByTime(VideoFile videoFile, Long beginTime, Long endTime) {
        Float duration = Float.valueOf(endTime - beginTime);
        String targetWaveNameWithSuffix = (++ProjectConstant.initID).toString() + ".wav";
        String targetPath = ProjectConstant.DEFAULTFILEPATH + targetWaveNameWithSuffix;
        File source = new File(videoFile.getPath());
        System.out.println("源文件是否存在："+source.exists());
        File target = new File(targetPath);
        if (target.exists() || target.isFile()) {
            System.out.println("目标文件是否删除:"+target.delete());
        }
        AudioAttributes audio = new AudioAttributes();
//        VideoAttributes video = new VideoAttributes();
        EncodingAttributes attrs = new EncodingAttributes();


        audio.setCodec("pcm_s16le");  //音频解码器
        audio.setBitRate(16000);   //音频比特率
        audio.setChannels(1);   //音频声道
        audio.setSamplingRate(16000);  //音频采样率

//        video.setCodec("pcm");     //视频解码器
//        video.setBitRate(160000);  //视频比特率
//        video.setFrameRate(15);   //视频帧率
//        video.setSize(new VideoSize(400, 300));  //视频尺寸


        attrs.setFormat("wav");
        attrs.setAudioAttributes(audio);
//        attrs.setVideoAttributes(video);
        attrs.setOffset(Float.valueOf(beginTime));
        attrs.setDuration(duration);

        try {
            MediaEncoder.encoder.encode(source, target, attrs);
            System.out.println("ok");
            System.out.println("文件是否生成：" + target.exists());

        } catch (EncoderException e) {
            e.printStackTrace();
        }
        return targetPath;
    }




}
