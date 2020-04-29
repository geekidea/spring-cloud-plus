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

import io.geekidea.cloud.generator.constant.GeneratorConstant;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 项目信息配置
 *
 * @author geekidea
 * @date 2020/3/13
 **/
@Data
@Accessors(chain = true)
public class ProjectConfig {

    /**
     * 分页参数后缀
     */
    private String pageQuerySuffix = GeneratorConstant.PAGE_QUERY;
    /**
     * Vo后缀
     */
    private String voSuffix = GeneratorConstant.VO_SUFFIX;
    /**
     * 分页参数父类全称，带包名
     */
    private String superPageQueryClass = GeneratorConstant.SUPER_PAGE_QUERY_CLASS;
    /**
     * 分页对象类全称，带包名
     */
    private String pagingClass = GeneratorConstant.PAGING_CLASS;
    /**
     * 分页信息类全称，带包名
     */
    private String pageInfoClass = GeneratorConstant.PAGE_INFO_CLASS;
    /**
     * 分页排序参数父类全称，带包名
     */
    private String superPageOrderQueryClass = GeneratorConstant.SUPER_PAGE_ORDER_PARAM_CLASS;
    /**
     * 公共id参数类全称，带包名
     */
    private String idParamClass = GeneratorConstant.ID_PARAM_CLASS;
    /**
     * 公共结果类全称，带包名
     */
    private String apiResultClass = GeneratorConstant.API_RESULT_CLASS;
}
