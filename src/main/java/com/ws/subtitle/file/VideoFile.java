package com.ws.subtitle.file;

import it.sauronsoftware.jave.EncoderException;
import com.ws.subtitle.util.MediaEncoder;

import java.io.File;

public class VideoFile {
    private String Path;
    private Long duration;
    private String nameWithSuffix;
    private String nameWithoutSuffix;
    private String suffix;
    private File file = null;


    public VideoFile(String path) {
        File file = new File(path);
        if (file.exists() || file.isFile()) {
            this.setPath(path);
            this.setNameWithoutSuffix(path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".")));
            this.setNameWithSuffix(path.substring(path.lastIndexOf("/") + 1));
            this.setSuffix(path.substring(path.lastIndexOf(".") + 1));
            try {
                this.setDuration(MediaEncoder.encoder.getInfo(file).getDuration());
            } catch (EncoderException e) {
                System.err.println("Video文件创建异常-无法解析");
                e.printStackTrace();
            }
        }


    }


    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getNameWithSuffix() {
        return nameWithSuffix;
    }

    public void setNameWithSuffix(String nameWithSuffix) {
        this.nameWithSuffix = nameWithSuffix;
    }

    public String getNameWithoutSuffix() {
        return nameWithoutSuffix;
    }

    public void setNameWithoutSuffix(String nameWithoutSuffix) {
        this.nameWithoutSuffix = nameWithoutSuffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
