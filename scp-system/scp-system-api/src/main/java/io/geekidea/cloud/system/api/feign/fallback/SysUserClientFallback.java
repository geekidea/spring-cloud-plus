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

package io.geekidea.cloud.system.api.feign.fallback;

import io.geekidea.cloud.system.api.entity.SysUser;
import io.geekidea.cloud.system.api.feign.SysUserClient;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author geekidea
 * @date 2020/4/10
 **/
@Slf4j
@Component
public class SysUserClientFallback implements SysUserClient {

    @Setter
    private Throwable throwable;

    @Override
    public String getUser(String name) {
        System.out.println("getUser...");
        log.error("错误原因：", throwable);
        return "请稍后再试...";
    }

    @Override
    public String addSysUser(SysUser sysUser) {
        System.out.println("addSysUser...");
        log.error("错误原因：", throwable);
        return "请稍后再试...";
    }

    @Override
    public String testError() {
        log.error("错误原因：", throwable);
        return "请稍后再试...";
    }
}
