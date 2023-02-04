package com.mali.travelstrategy.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户查询条件实体
 * @author By-mali
 */
@Data
public class UserParam {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "昵称")
    private String nickName;


}
