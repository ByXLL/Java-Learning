package com.mali.travelstrategy.dto;

import lombok.Data;

/**
 * 用户修改密码dto
 * @author By-mali
 */
@Data
public class UserPasswordDto {
    private Integer id;
    private String oldPassword;
    private String newPassword;
}
