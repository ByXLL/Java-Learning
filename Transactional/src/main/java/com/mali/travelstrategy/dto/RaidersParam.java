package com.mali.travelstrategy.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 攻略 查询条件实体
 * @author By-mali
 */
@Data
public class RaidersParam implements Serializable {
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "攻略标题")
    private String title;

    @ApiModelProperty(value = "作者id")
    private Integer authorId;

    @ApiModelProperty(value = "作者名字")
    private String author;

    @ApiModelProperty(value = "查询条件类型，1->综合、2->最热、3->最新")
    private Integer selectType;
}
