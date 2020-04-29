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

package io.geekidea.cloud.generator.constant;

import com.baomidou.mybatisplus.core.toolkit.StringPool;

import java.io.File;

/**
 * 生成器常量
 *
 * @author geekidea
 * @date 2020/3/12
 **/
public interface GeneratorConstant {

    /**
     * 用户目录
     */
    String USER_DIR = "user.dir";
    /**
     * java目录 src/main/java
     */
    String JAVA_DIR = File.separator + "src" + File.separator + "main" + File.separator + "java";
    /**
     * resources目录 src/main/resources
     */
    String RESOURCES_DIR = File.separator + "src" + File.separator + "main" + File.separator + "resources";
    /**
     * Mapper目录 mapper
     */
    String MAPPER_DIR = "mapper";
    /**
     * Mapper XML目录 src/main/resources/mapper
     */
    String MAPPER_XML_DIR = RESOURCES_DIR + File.separator + "mapper";
    /**
     * 参数
     */
    String PARAM = "param";
    /**
     * 参数
     */
    String QUERY = "query";
    /**
     * 分页参数
     */
    String PAGE_QUERY = "PageQuery";
    /**
     * VO
     */
    String VO = "vo";
    /**
     * 查询VO
     */
    String VO_SUFFIX = "Vo";
    /**
     * Service
     */
    String SERVICE = "Service";
    /**
     * Mapper
     */
    String MAPPER = "Mapper";
    /**
     * api
     */
    String API = "api";
    /**
     * entity
     */
    String ENTITY = "entity";
    /**
     * dto
     */
    String DTO = "dto";
    /**
     * fegin
     */
    String FEIGN = "feign";
    /**
     * client
     */
    String CLIENT = "client";
    /**
     * factory
     */
    String FACTORY = "factory";
    /**
     * fallback
     */
    String FALLBACK = "fallback";
    /**
     * dot
     */
    String DOT = ".";
    /**
     * API entity包
     */
    String API_ENTITY_PACKAGE = API + DOT + ENTITY;
    /**
     * API entity包路径
     */
    String API_ENTITY_PACKAGE_PATH = API + File.separator + ENTITY;
    /**
     * API dto包路径
     */
    String API_DTO_PACKAGE_PATH = API + File.separator + DTO;
    /**
     * API query包路径
     */
    String API_QUERY_PACKAGE_PATH = API + File.separator + QUERY;
    /**
     * API vo包路径
     */
    String API_VO_PACKAGE_PATH = API + File.separator + VO;
    /**
     * API feign client包路径
     */
    String API_FEIGN_CLIENT_PACKAGE_PATH = API + File.separator + FEIGN;
    /**
     * API feign factory包路径
     */
    String API_FEIGN_CLIENT_FACTORY_PACKAGE_PATH = API_FEIGN_CLIENT_PACKAGE_PATH + File.separator + FACTORY;
    /**
     * API feign factory包路径
     */
    String API_FEIGN_CLIENT_FALLBACK_PACKAGE_PATH = API_FEIGN_CLIENT_PACKAGE_PATH + File.separator + FALLBACK;


    /**
     * Entity template路径
     */
    String ENTITY_TEMPLATE_PATH = File.separator + "templates" + File.separator + "entity.java.vm";
    /**
     * Mapper XML template路径
     */
    String MAPPER_XML_TEMPLATE_PATH = File.separator + "templates" + File.separator + "mapper.xml.vm";
    /**
     * 分页参数 template路径
     */
    String PAGE_QUERY_TEMPLATE_PATH = File.separator + "templates" + File.separator + "pageQuery.java.vm";
    /**
     * 查询VO template路径
     */
    String VO_TEMPLATE_PATH = File.separator + "templates" + File.separator + "vo.java.vm";
    /**
     * 查询Feign Client template路径
     */
    String FEIGN_CLIENT_TEMPLATE_PATH = File.separator + "templates" + File.separator + "feignClient.java.vm";
    /**
     * Feign Factory template路径
     */
    String FEIGN_CLIENT_FACTORY_TEMPLATE_PATH = File.separator + "templates" + File.separator + "feignClientFactory.java.vm";
    /**
     * Feign Fallback template路径
     */
    String FEIGN_CLIENT_FALLBACK_TEMPLATE_PATH = File.separator + "templates" + File.separator + "feignClientFallback.java.vm";
    /**
     * 乐观锁属性名称
     */
    String VERSION = "version";
    /**
     * 逻辑删除属性名称
     */
    String DELETED = "deleted";
    /**
     * Service名称
     */
    String SERVICE_NAME = "%sService";
    /**
     * 自定义继承的Entity类全称，带包名
     */
    String SUPER_ENTITY_CLASS = "io.geekidea.cloud.common.core.entity.BaseEntity";
    /**
     * 自定义继承的Controller类全称，带包名
     */
    String SUPER_CONTROLLER_CLASS = "io.geekidea.cloud.common.core.controller.BaseController";
    /**
     * 自定义继承的Service类全称，带包名
     */
    String SUPER_SERVICE_CLASS = "io.geekidea.cloud.common.core.service.BaseService";
    /**
     * 自定义继承的ServiceImpl类全称，带包名
     */
    String SUPER_SERVICE_IMPL_CLASS = "io.geekidea.cloud.common.core.service.impl.BaseServiceImpl";
    /**
     * 分页参数父类全称，带包名
     */
    String SUPER_PAGE_QUERY_CLASS = "BasePageQuery";
    /**
     * 分页排序参数父类全称，带包名
     */
    String SUPER_PAGE_ORDER_PARAM_CLASS = "io.geekidea.cloud.common.core.pagination.BasePageOrderQuery";
    /**
     * 公共id参数类全称，带包名
     */
    String ID_PARAM_CLASS = "io.geekidea.cloud.common.core.dto.param.IdParam";
    /**
     * 分页对象类全称，带包名
     */
    String PAGING_CLASS = "io.geekidea.cloud.common.core.pagination.Paging";
    /**
     * 分页信息类全称，带包名
     */
    String PAGE_INFO_CLASS = "io.geekidea.cloud.common.core.pagination.PageInfo";
    /**
     * 公共结果类全称，带包名
     */
    String API_RESULT_CLASS = "io.geekidea.cloud.common.core.api.ApiResult";
    /**
     * 分组验证Add.class类路径
     */
    String VALIDATOR_ADD_PACKAGE = "io.geekidea.cloud.common.core.validator.groups.Add";
    /**
     * 分组验证Update.class类路径
     */
    String VALIDATOR_UPDATE_PACKAGE = "io.geekidea.cloud.common.core.validator.groups.Update";
    /**
     * Feign config类路径
     */
    String FEIGN_CONFIG_PACKAGE = "io.geekidea.cloud.framework.config.feign.FeignConfiguration";
}
