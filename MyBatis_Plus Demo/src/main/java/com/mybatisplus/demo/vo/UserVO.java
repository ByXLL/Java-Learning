package com.mybatisplus.demo.vo;

import lombok.Data;

/**
 * 用户 user 视图模型
 * @author By-Lin
 */
@Data
public class UserVO {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
