package com.mali.travelstrategy.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.sql.Timestamp;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 景点信息
 * @author By-mali
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Attractions对象", description="景点信息")
public class Attractions implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "景点名称")
    private String name;

    @ApiModelProperty(value = "景点标题")
    private String title;

    @ApiModelProperty(value = "封面图")
    private String coverPic;

    @ApiModelProperty(value = "图片信息")
    private String pic;

    @ApiModelProperty(value = "正文信息")
    private String content;

    @ApiModelProperty(value = "票价")
    private BigDecimal fare;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "星级 0->无， 1->1星，2->2星，3->3星，4->4星，5->5星")
    private Integer starRating;

    @ApiModelProperty(value = "编辑时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
