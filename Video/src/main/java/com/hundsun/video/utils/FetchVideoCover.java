package com.hundsun.video.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FetchVideoCover {
    public static String ffmpegEXE = "D:/ffmpeg/bin/ffmpeg.exe";

    public static void getCover(String videoInputPath, String coverOutputPath) throws IOException {
        //	ffmpeg.exe -ss 00:00:01 -i spring.mp4 -vframes 1 bb.jpg
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);

        // 指定截取第1秒
        command.add("-ss");
        command.add("00:00:01");

        command.add("-y");
        command.add("-i");
        command.add(videoInputPath);

        command.add("-vframes");
        command.add("1");

        command.add(coverOutputPath);
        String str = "";
        for (String c : command) {
            str += " ";
            str += c;
        }
        System.out.println(str);
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
