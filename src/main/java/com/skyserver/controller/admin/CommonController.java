package com.skyserver.controller.admin;

import com.skycommon.result.Result;
import com.skycommon.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName CommonController
 * @Author shuai
 * @create 2023/8/1 12:03
 * @Instruction
 */
@RestController
@Slf4j
@RequestMapping("/admin/common")
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {//传递文件指定文件参数！！！
        log.info("文件上传：{}",file);
        String extName=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));//文件拓展名.jpg等
        String objectName= UUID.randomUUID().toString()+extName;//随机生成文件名
        String url = aliOssUtil.upload(file.getBytes(), objectName);
        return Result.success(url);
    }
}
