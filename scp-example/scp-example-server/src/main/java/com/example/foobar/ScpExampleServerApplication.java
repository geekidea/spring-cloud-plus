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

package com.example.foobar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * spring-cloud-plus Example Server启动类
 *
 * @author geekidea
 * @date 2020/4/10
 **/
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"io.geekidea.cloud"})
@MapperScan({"io.geekidea.cloud.**.mapper", "com.example.**.mapper"})
@SpringBootApplication(scanBasePackages = {"io.geekidea.cloud", "com.example"})
public class ScpExampleServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScpExampleServerApplication.class, args);
    }

}
