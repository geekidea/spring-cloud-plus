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

package io.geekidea.cloud.system.server.controller;

import io.geekidea.cloud.system.api.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekidea
 * @date 2020/4/10
 **/
@Slf4j
@RestController
@RefreshScope
@RequestMapping("/sysUser")
public class SysUserController {

    @GetMapping("/info")
    public String getUser(String name){
        System.out.println("name = " + name);
        return "hello " + name;
    }

    @PostMapping("/add")
    public String addSysUser(SysUser sysUser){
        System.out.println("sysUser = " + sysUser);
        return sysUser.getName();
    }

    @PostMapping("/testError")
    public String testError(){
        System.out.println(1/0);
        return "test error...";
    }

}
