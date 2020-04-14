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

package io.geekidea.cloud.system.api.feign;

import io.geekidea.cloud.framework.config.FeignConfiguration;
import io.geekidea.cloud.system.api.entity.SysUser;
import io.geekidea.cloud.system.api.feign.factory.SysUserClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author geekidea
 * @date 2020/4/10
 **/
@FeignClient(name = "scp-system", path = "/sysUser", fallbackFactory = SysUserClientFallbackFactory.class, configuration = FeignConfiguration.class)
public interface SysUserClient {

    @GetMapping("/info")
    public String getUser(@RequestParam("name") String name);

    @PostMapping("/add")
    public String addSysUser(SysUser sysUser);

    @PostMapping("/testError")
    public String testError();

}
