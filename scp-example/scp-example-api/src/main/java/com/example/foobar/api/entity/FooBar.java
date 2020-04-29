package com.example.foobar.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import io.geekidea.cloud.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import io.geekidea.cloud.common.core.validator.groups.Update;

/**
 * FooBar
 *
 * @author geekidea
 * @since 2020-04-30
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "FooBar对象")
public class FooBar extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "Name不能为空")
    @ApiModelProperty("Name")
    private String name;

    @ApiModelProperty("Foo")
    private String foo;

    @NotBlank(message = "Bar不能为空")
    @ApiModelProperty("Bar")
    private String bar;

    @ApiModelProperty("Remark")
    private String remark;

    @ApiModelProperty("State，0：Disable，1：Enable")
    private Integer state;

    @ApiModelProperty("Version")
    @Version
    private Integer version;

    @ApiModelProperty("Create Time")
    private Date createTime;

    @ApiModelProperty("Update Time")
    private Date updateTime;

}
