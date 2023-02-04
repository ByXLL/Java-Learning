package com.mali.travelstrategy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户关注列表
 * @author By-mali
 */
@Data
@ApiModel(value="UserAttention对象", description="用户关注表")
public class UserAttentionVO implements Serializable {
    @ApiModelProperty(value = "关系表主键")
    private Integer utId;

    @ApiModelProperty(value = "关注的用户对象的id")
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

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

    @ApiModelProperty(value = "关注时间")
    private Date createTime;
}
