package com.mali.travelstrategy.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息vo
 * @author By-mali
 */
@Data
public class UserVO {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "用户角色 1-> 后台系统管理员，2-> 普通用户")
    private Integer role;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "性别 0->女，1->男")
    private Integer sex;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "状态 0->禁用，1->正常")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
