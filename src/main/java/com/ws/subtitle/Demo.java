package com.ws.subtitle;

import com.ws.subtitle.constant.ProjectConstant;
import com.ws.subtitle.file.VideoFile;
import com.ws.subtitle.util.Audio2Text;
import com.ws.subtitle.util.AudioTaker;

public class Demo {
    public static void main(String[] args) {
        VideoFile videoFile = new VideoFile(ProjectConstant.AUDIOFILE_PATH);

        String fullTimeAudioFilePath = AudioTaker.takeAll(videoFile);
        System.out.println(fullTimeAudioFilePath);
        System.out.println();

        String audioFilePath = AudioTaker.takeByTime(videoFile, 0L, 10L);
        System.out.println(audioFilePath);
        Audio2Text.Audio2text(audioFilePath);
    }
}
