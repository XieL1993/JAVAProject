package com.hundsun.house.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    String test();

    List<String> getImgPaths(List<MultipartFile> files);
}
