package com.mybatisplus.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 用户实体
 * @author By-Lin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * TableId 设置id自增
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    /**
     * 添加 乐观锁 注解
     */
    @Version
    private Integer version;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDel;
    /**
     * 通过 TableField 列属性去设置 FieldFill 填充策略
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 在插入和更新的时候设置自动填充
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
