package com.hundsun.video.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MergeVideoMp3 {
    public static String ffmpegEXE = "D:/ffmpeg/bin/ffmpeg.exe";

    //		ffmpeg.exe -i a.mp4 -i b.mp3 -t 7 -y 新的视频.mp4
    public static void convertor(String videoInputPath, String mp3InputPath, double seconds, String videoOutputPath) throws IOException {
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);

        command.add("-i");  // -i 选择被执行文件
        command.add(videoInputPath);

        command.add("-i");
        command.add(mp3InputPath);

        command.add("-t");
        command.add(String.valueOf(seconds));

        command.add("-y");
        command.add(videoOutputPath);
//        String str = "";
//        for (String c : command) {
//            str += " ";
//            str += c;
//        }
//        System.out.println(str);
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        while (br.readLine() != null) {
        }
        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }
    }
}
