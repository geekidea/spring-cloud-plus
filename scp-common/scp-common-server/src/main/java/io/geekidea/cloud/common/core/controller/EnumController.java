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

package io.geekidea.cloud.common.core.controller;

import io.geekidea.cloud.common.core.api.ApiResult;
import io.geekidea.cloud.common.core.enums.BaseEnum;
import io.geekidea.cloud.common.core.vo.EnumVo;
import io.geekidea.cloud.common.core.util.EnumUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 展示实现BaseEnum接口的所有枚举值
 * </p>
 *
 * @author geekidea
 * @date 2018/11/02
 */
@RestController
@Slf4j
//@OperationLogIgnore
@Api(value = "枚举字典", tags = {"枚举字典"})
public class EnumController {

    private static final List<String> FRAMEWORK_ENUM_PACKAGES = Arrays.asList(
            "io.geekidea.cloud.common.enums",
            "io.geekidea.cloud.system.enums");

    /**
     * 枚举包路径
     */
    @Value("${spring-cloud-plus.enum-packages:null}")
    private List<String> enumPackages;

    @GetMapping("/enum")
    public ApiResult<Map<String, Map<Integer, EnumVo<? extends BaseEnum>>>> enumList() {
        log.debug("enumList...");
        return ApiResult.ok(EnumUtil.getEnumMap());
    }

    @PostConstruct
    public void init() {
        try {
            if (enumPackages == null) {
                enumPackages = new ArrayList<>();
            }
            enumPackages.addAll(FRAMEWORK_ENUM_PACKAGES);

            // 获取BaseEnum接口的所有实现
            log.debug("enumPackages:" + enumPackages);
            Reflections reflections = new Reflections(enumPackages);
            Set<Class<? extends BaseEnum>> set = reflections.getSubTypesOf(BaseEnum.class);
            if (CollectionUtils.isEmpty(set)) {
                return;
            }
            // 循环获取BaseEnum枚举
            for (Class<? extends BaseEnum> clazz : set) {
                BaseEnum[] enumConstants = clazz.getEnumConstants();
                Map<Integer, EnumVo<? extends BaseEnum>> enumVoMap = new ConcurrentHashMap<>(enumConstants.length);
                for (BaseEnum baseEnum : enumConstants) {
                    Integer code = baseEnum.getCode();
                    String desc = baseEnum.getDesc();
                    EnumVo<BaseEnum> enumVo = new EnumVo<BaseEnum>()
                            .setCode(code)
                            .setDesc(desc)
                            .setBaseEnum(baseEnum);
                    enumVoMap.put(code, enumVo);
                }
                // 设置map
                EnumUtil.getEnumMap().put(clazz.getName(), enumVoMap);
            }
            log.debug("enumMap:{}", EnumUtil.getEnumMap());
        } catch (Exception e) {
            log.error("获取BaseEnum枚举map异常", e);
        }
    }

}
