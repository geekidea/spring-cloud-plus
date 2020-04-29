package com.example.foobar.api.query;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import io.geekidea.cloud.common.core.pagination.BasePageOrderQuery;

/**
 * <pre>
 * FooBar 分页查询对象
 * </pre>
 *
 * @author geekidea
 * @date 2020-04-30
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "FooBar分页查询")
public class FooBarPageQuery extends BasePageOrderQuery {
    private static final long serialVersionUID = 1L;
}
