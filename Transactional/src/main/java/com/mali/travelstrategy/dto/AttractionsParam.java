package com.mali.travelstrategy.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 景点查询条件
 * @author By-mali
 */
@Data
public class AttractionsParam {
    @ApiModelProperty(value = "景点名称")
    private String name;

    @ApiModelProperty(value = "最小票价")
    private BigDecimal minFare;

    @ApiModelProperty(value = "最大票价")
    private BigDecimal maxFare;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "星级 0->无， 1->1星，2->2星，3->3星，4->4星，5->5星")
    private Integer starRating;
}
