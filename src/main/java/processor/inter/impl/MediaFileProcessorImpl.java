package processor.inter.impl;

import constant.ProjectConstant;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import processor.inter.MediaFileProcessor;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MediaFileProcessorImpl implements MediaFileProcessor {

    private Encoder encoder = new Encoder();





    /**
     * 用于提取视频文件中的指定时间段的音频文件并进行转码存储，附加一份时间轴信息
     *
     * @param videoPath 视频文件路径地址
     * @param beginTime 要截取的时间起始点
     * @param endTime   要截取的时间终止点
     * @return 存储后的音频文件路径
     */
    public String saveAudioByTime(String videoPath, Long beginTime, Long endTime) {

        videoPath = ProjectConstant.AUDIOFILEPATH;

        try {
            MultimediaInfo multimediaInfo = encoder.getInfo(new File(videoPath));


        } catch (EncoderException e) {
            e.printStackTrace();
        }


        //定义音频文件及信息文件文件名
        String audioFileName = this.genNewID().toString() + ".pcm";
        String infoFileName = audioFileName + ".inf";


        //提取并转化按成pcm,并写入信息文件
        try (FileInputStream fileInputStream = new FileInputStream(videoPath);//读入路径
             FileOutputStream fileOutputStream = new FileOutputStream(ProjectConstant.DEFAULTFILEPATH + audioFileName);//音频文件写出路径
             FileWriter fileWriter = new FileWriter(ProjectConstant.DEFAULTFILEPATH + infoFileName)//信息文件写出路径
        ) {

            byte[] tempBytes = new byte[100];
            int byteRead;
            while ((byteRead = fileInputStream.read(tempBytes)) != -1) {

                fileOutputStream.write(byteRead);

            }
            fileOutputStream.flush();

            //写inf文件
            fileWriter.write(beginTime.toString() + "/n");
            fileWriter.write(endTime.toString() + "/n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ProjectConstant.DEFAULTFILEPATH + audioFileName;
    }

    public String getVideoType(String path) {
        return null;
    }

    public Integer genNewID() {
        return ++ProjectConstant.initID;
    }

    public String audio2Text(String audioPath) {
        try (FileInputStream in = new FileInputStream(audioPath)) {
            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
            //一次读多个字节
            byte[] tempBytes = new byte[100];
            int byteRead = 0;
            //读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteRead = in.read(tempBytes)) != -1) {
                System.out.write(tempBytes, 0, byteRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static String postASR(String requestUrl, String params) {
        System.out.println(params);
        String generalUrl = requestUrl;//这里不需要对接口地址拼接access_token参数 切记！！！
        System.out.println("发送的连接为:" + generalUrl);

        URL url = null;


        try {
            url = new URL(generalUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 打开和URL之间的连接

            System.out.println("打开链接，开始发送请求" + new Date().getTime() / 1000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 得到请求的输出流对象
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(params);
            out.flush();
            out.close();
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> headers = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : headers.keySet()) {
                System.out.println(key + "--->" + headers.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in;
            if (requestUrl.contains("nlp")) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            }
            StringBuffer result = null;
            String getLine;
            while ((getLine = in.readLine()) != null) {
                result.append(getLine);
            }
            in.close();
            System.out.println("请求结束" + new Date().getTime() / 1000);
            assert result != null;
            System.out.println("result:" + result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getSuffix(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }

    public String getFileNameWithSuffix(String path) {
        return path.substring(path.lastIndexOf("/") + 1);
    }

    public String getFileNameWithoutSuffix(String path) {
        return path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
    }
}
