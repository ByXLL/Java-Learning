package com.mali.travelstrategy.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author By-Lin
 */
@Data
public class VersionVO implements Serializable {
    private Integer id;

    private String version;

    private List<VersionVO> list;
}
