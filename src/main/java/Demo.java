import file.VideoFile;
import util.AudioTaker;

public class Demo {
    public static void main(String[] args) {
        VideoFile videoFile = new VideoFile("d:/商务.mov");
        String audioFilePath = AudioTaker.takeByTime(videoFile, 0L, 10L);
        System.out.println(audioFilePath);

    }
}
