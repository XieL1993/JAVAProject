package com.hundsun.video.controller;


import com.hundsun.video.entity.Bgm;
import com.hundsun.video.entity.Result;
import com.hundsun.video.entity.VUser;
import com.hundsun.video.entity.Video;
import com.hundsun.video.enums.ResultEnums;
import com.hundsun.video.exception.VException;
import com.hundsun.video.properties.AttachProperties;
import com.hundsun.video.service.IBgmService;
import com.hundsun.video.service.IVUserService;
import com.hundsun.video.service.IVideoService;
import com.hundsun.video.utils.FetchVideoCover;
import com.hundsun.video.utils.MergeVideoMp3;
import com.hundsun.video.utils.ResultUtils;
import com.hundsun.video.utils.UploadUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/api/video")
@Api(tags = {"video接口"})
public class VideoController {
    @Autowired
    private IBgmService bgmService;
    @Autowired
    private IVUserService userService;
    @Autowired
    private IVideoService videoService;
    @Autowired
    private AttachProperties properties;
    @Autowired
    private UploadUtils uploadUtils;

    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    public Result upload(Integer userId,
                         Integer bgmId, double videoSeconds,
                         int videoWidth, int videoHeight,
                         String desc, MultipartFile file) throws IOException {
        if (file == null) {
            throw new VException(ResultEnums.BAD_PARAMS);
        }
        VUser user = userService.selectById(userId);
        if (user == null) {
            throw new VException(ResultEnums.ERROR_USER);
        }
        // 附件存储路径
        String[] dirs = uploadUtils.getDir(file.getOriginalFilename(), userId);
        // 保存视频
        String finalVideoPath = dirs[0];
        File outFile = new File(properties.getRoot() + finalVideoPath);
        file.transferTo(outFile);
        // 合并BGM
        String videoInputPath = finalVideoPath;
        if (bgmId != 0) {
            Bgm bgm = bgmService.selectById(bgmId);
            if (bgm != null) {
                String mp3InputPath = properties.getRoot() + bgm.getPath();
                finalVideoPath = dirs[1];
                MergeVideoMp3.convertor(properties.getRoot() + videoInputPath, mp3InputPath, videoSeconds, properties.getRoot() + finalVideoPath);
            }
        }
        // 截取视频封面
        String coverPath = dirs[2];
        FetchVideoCover.getCover(properties.getRoot() + videoInputPath, properties.getRoot() + coverPath);
        Video video = new Video();
        video.setAudioId(bgmId);
        video.setUserId(userId);
        video.setVideoDesc(desc);
        video.setVideoPath(finalVideoPath);
        video.setVideoSeconds((float) videoSeconds);
        video.setVideoWidth(videoWidth);
        video.setVideoHeight(videoHeight);
        video.setCoverPath(coverPath);
        video.setLikeCounts(0l);
        video.setStatus(1);
        video.setCreateTime(new Date());
        boolean insert = videoService.insert(video);
        if (insert) {
            return ResultUtils.success();
        } else {
            throw new VException(ResultEnums.UNKONW_ERROR);
        }
    }

    @GetMapping(value = "/list")
    public Result findAll(String userId, String videoDesc, Integer isSaveRecord,
                          Integer pageSize, Integer currentPage) {
        if (pageSize == null|| currentPage ==null) {
            throw new VException(ResultEnums.BAD_PARAMS);
        }
        return ResultUtils.success(videoService.getAllVideos(userId, videoDesc, isSaveRecord, pageSize, currentPage));

    }
}
