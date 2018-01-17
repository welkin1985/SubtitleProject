package com.ws.subtitle.util;

import com.ws.subtitle.file.VideoFile;

/**
 *
 */
public class WavPicMaker {
    public static String getAllAudio(VideoFile videoFile) {
        return AudioTaker.takeAll(videoFile);
    }
}
