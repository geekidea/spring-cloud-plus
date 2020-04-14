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

package io.geekidea.cloud.example.server.controller;

import io.geekidea.cloud.system.api.feign.SysUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author geekidea
 * @date 2020/4/10
 **/
@RestController
@RequestMapping("/example")
public class ExampleController {

    @Autowired
    private SysUserClient sysUserClient;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("test...");
        return "test..." + System.currentTimeMillis();
    }

    @GetMapping
    public String example() {
        System.out.println("example...");
        String string = sysUserClient.getUser("lllllllll");
        System.out.println("string = " + string);
        return string;
    }

    @GetMapping("/testError")
    public String testError() {
        System.out.println("testError....");
        String string = sysUserClient.testError();
        System.out.println("string = " + string);
        return string;
    }


    @GetMapping("/xxx")
    public String xxx() {
        System.out.println("xxx");
        String string = restTemplate.getForObject("http://scp-system/sysUser/info?name=zzzzzzzz", String.class);
        System.out.println("string = " + string);
        return string;
    }
}
