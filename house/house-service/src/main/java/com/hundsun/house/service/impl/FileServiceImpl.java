package com.hundsun.house.service.impl;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.hundsun.house.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Value("${file.path}")
    private String filePath;

    @Override
    public String test() {
        return filePath;
    }

    @Override
    public List<String> getImgPaths(List<MultipartFile> files) {
        List<String> paths = Lists.newArrayList();
        files.forEach(file -> {
            File localFile;
            if (!file.isEmpty()) {
                try {
                    localFile = saveToLocal(file);
                    String path = StringUtils.substringAfterLast(localFile.getAbsolutePath(), filePath);
                    path = path.replaceAll("\\\\", "/");
                    paths.add(path);
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        return paths;
    }

    private File saveToLocal(MultipartFile file) throws IOException {
        File newFile = new File(filePath + File.separator + Instant.now().getEpochSecond() + File.separator + file.getOriginalFilename());
        if (!newFile.exists()) {
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
        }
        Files.write(file.getBytes(), newFile);
        return newFile;
    }
}
