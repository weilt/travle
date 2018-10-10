package com.plus.admin.controller.api;

import com.admin.service.admin.UploadJsonService;
import com.alibaba.fastjson.JSONObject;
import com.plus.admin.controller.api.result.ResultCode;
import com.plus.admin.controller.api.result.ResultEntity;
import com.plus.admin.controller.base.BaseApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/22 09:28
 * @Description: 小程序上传图片
 */
@Api(value = "API - UploadFileController", description = "上传图片")
@RestController
public class UploadFileController extends BaseApiController {

    @Autowired
    private UploadJsonService uploadJsonService;

    /**
     * 阿里云上传
     * @param uploadFile
     * @return
     */
    @ApiOperation(value = "保存地址" , notes = "保存地址", response = ResultEntity.class)
    @PostMapping("/api/uploadoss")
    public ResultEntity<String> upload(@RequestParam("file") MultipartFile uploadFile){
        String url = uploadJsonService.uploadOss(uploadFile);
        if (null == url){
            return ResultEntity.build(ResultCode.UPLOAD_IMG_ERR);
        } else {
            return ResultEntity.build(url);
        }
    }
}
