package file;

import java.io.*;
import java.util.ArrayList;

public class SubtitleFile {
    private String name;
    private String suffix;
    private String charCode;
    private String filePath;
    private ArrayList<String> subtitleArray;
    private Long lineIndex = 0L;
    private File subtitleFile = null;

    public SubtitleFile(String filePath) {
        this.setFilePath(filePath);
        this.setName(filePath.substring(filePath.lastIndexOf("/") + 1));
        this.setSuffix(filePath.substring(filePath.lastIndexOf(".") + 1));
    }


    public ArrayList<String> genSubtitleArray() {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(new File(this.getFilePath())));
        ) {
            String temp = null;
            this.setSubtitleArray(null);
            while ((temp = fileReader.readLine()) != null) {
                this.getSubtitleArray().add(fileReader.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.getSubtitleArray();
    }


    public String getSubtitleByID(String id) {
        return this.getSubtitleArray().get(Integer.getInteger(id));
    }

    public String getSubtitleByID(Integer id) {
        return this.getSubtitleArray().get(id);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> getSubtitleArray() {
        return subtitleArray;
    }

    public void setSubtitleArray(ArrayList<String> subtitleArray) {
        this.subtitleArray = subtitleArray;
    }

    public Long getLineIndex() {
        return lineIndex;
    }

    public void setLineIndex(Long lineIndex) {
        this.lineIndex = lineIndex;
    }

    public File getSubtitleFile() {
        return subtitleFile;
    }

    public void setSubtitleFile(File subtitleFile) {
        this.subtitleFile = subtitleFile;
    }
}
