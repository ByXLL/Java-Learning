package com.mali.travelstrategy.util;

import com.alibaba.fastjson.JSON;
import com.mali.travelstrategy.entity.ApiResult;
import com.mali.travelstrategy.enums.HttpCodeEnum;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class QiniuUtil {

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Value("${qiniu.path}")
    private String path;


    public ApiResult Upload(byte[] file, String key) {
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(file, key, upToken, null, null,false);
                //解析上传成功的结果
                DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
                Map<String,Object> fileInfo = new HashMap<>(16);
                fileInfo.put("filePath",path + "/" + putRet.key);
                return new ApiResult(HttpCodeEnum.SUCCESS.getCode(),"上传成功", fileInfo );
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(HttpCodeEnum.ERROR.getCode(),"上传失败");
        }
        return new ApiResult(HttpCodeEnum.ERROR.getCode(),"上传失败");
    }
}
