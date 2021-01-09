package com.nowcoder.community.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/***
 * 工具类
 */
public class CommunityUtil {
    /***
     * 生成随机字符串
     * @return  随机字符串
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * MD5 加密
     * @param key   加密的值
     * @return  加密后的值
     */
    public static String md5(String key) {
        // 判断是否为空
        if(StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
    //

    /***
     * 获取json字符串
     * @param code  状态值
     * @param msg   提示信息
     * @param map   数据
     * @return      json字符串
     */
    public static String getJsonString(int code, String msg, Map<String,Object> map) {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> data = new HashMap<>();
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        if(map != null) {
            for (String key : map.keySet()) {
                data.put(key,map.get(key));
            }
            jsonObject.put("data",data);
        }
        return jsonObject.toJSONString();
    }

    /***
     * 重载 获取json字符串
     * @param code  状态值
     * @param msg   提示信息
     * @return  json字符串
     */
    public static String getJsonString(int code, String msg) {
        return getJsonString(code,msg,null);
    }
    /***
     * 重载 获取json字符串
     * @param code  状态值
     * @return  json字符串
     */
    public static String getJsonString(int code) {
        return getJsonString(code,null,null);
    }

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age",20);
        System.out.println(getJsonString(0,"数据获取成功",map));
    }
}
