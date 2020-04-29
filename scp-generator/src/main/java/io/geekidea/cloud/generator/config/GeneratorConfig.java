/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.cloud.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 生成配置
 *
 * @author geekidea
 * @date 2020/3/13
 **/
@Data
@Accessors(chain = true)
public class GeneratorConfig {
    /**
     * 代码生成策略
     */
    @NestedConfigurationProperty
    public GeneratorStrategy generatorStrategy = GeneratorStrategy.ALL;
    /**
     * 是否生成实体类
     */
    private boolean generatorEntity;
    /**
     * 是否生成控制器
     */
    private boolean generatorController;
    /**
     * 是否生成service接口
     */
    private boolean generatorService;
    /**
     * 是否生成service实现类
     */
    private boolean generatorServiceImpl;
    /**
     * 是否生成Mapper
     */
    private boolean generatorMapper;
    /**
     * 是否生成Mapper XML
     */
    private boolean generatorMapperXml;
    /**
     * 是否生成查询参数
     */
    private boolean generatorPageQuery;
    /**
     * 是否生成查询VO
     */
    private boolean generatorVo;
    /**
     * 是否生成Shiro RequiresPermissions 注解
     */
    private boolean requiresPermissions;
    /**
     * 分页列表查询是否排序 true：有排序参数/false：无
     */
    private boolean pageListOrder;
    /**
     * 是否生成validation校验，true：生成/false：不生成
     */
    private boolean paramValidation;
    /**
     * 是否生成Swagger tags
     * true: @Api(value = "系统用户API",tags = {"系统用户"})
     * false: @Api("系统用户API")
     */
    private boolean swaggerTags;
    /**
     * 是否生成系统操作日志注解：@OperationLog
     */
    private boolean operationLog;

    /**
     * 是否生成Feign,包含client/fallback/fallbackFactory/spring.factories
     */
    private boolean generatorFeign;

    /**
     * 是否生成Feign Client
     */
    private boolean generatorFeignClient;

    /**
     * 是否生成Feign Client
     */
    private boolean generatorFeignClientFallback;

    /**
     * 是否生成Feign Client
     */
    private boolean generatorFeignClientFactory;

}
