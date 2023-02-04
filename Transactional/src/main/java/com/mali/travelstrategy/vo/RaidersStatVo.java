package com.mali.travelstrategy.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 攻略点赞 vo
 * @author By-Lin
 */
@Data
public class RaidersStatVo {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "攻略id")
    private Integer raidersId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "点赞时间")
    private Date createTime;
}
