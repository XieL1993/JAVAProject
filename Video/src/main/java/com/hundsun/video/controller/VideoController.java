package com.hundsun.video.controller;


import com.hundsun.video.entity.Result;
import com.hundsun.video.enums.ResultEnums;
import com.hundsun.video.exception.VException;
import com.hundsun.video.utils.ResultUtils;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/video")
@Api(tags = {"video接口"})
public class VideoController {
    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    public Result upload(String userId,
                         String bgmId, double videoSeconds,
                         int videoWidth, int videoHeight,
                         String desc, MultipartFile file) {
        if (StringUtils.isBlank(userId) || file == null) {
            throw new VException(ResultEnums.BAD_PARAMS);
        }
        String filename = file.getOriginalFilename();
        return ResultUtils.success();
    }

}
