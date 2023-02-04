package com.mali.travelstrategy.vo;

import com.mali.travelstrategy.entity.Raiders;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 攻略详情 vo实体
 * @author By-mali
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RaidersDetailsVO extends Raiders {

    @ApiModelProperty(value = "点赞人数")
    private Integer startCount;

    @ApiModelProperty(value = "作者头像")
    private String avatar;

    @ApiModelProperty(value = "是否已经点赞 0->否、 1->是")
    private Boolean isStat;
}
