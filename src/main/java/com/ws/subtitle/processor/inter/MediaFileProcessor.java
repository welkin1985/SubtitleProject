package com.ws.subtitle.processor.inter;

public interface MediaFileProcessor {



    String saveAudioByTime(String videoPath, Long beginTime, Long endTime);

    String getVideoType(String path);

    Integer genNewID();

    String audio2Text(String audioPath);

    String getSuffix(String path);

    String getFileNameWithSuffix(String path);
    String getFileNameWithoutSuffix(String path);

}
