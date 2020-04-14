# spring-cloud-plus

### [GITHUB](https://github.com/geekidea/spring-cloud-plus) | [GITEE](https://gitee.com/geekidea/spring-cloud-plus)

### [springcloud.plus](http://springcloud.plus)

## spring-cloud-plus V1.0 Technology Stack
Component | Version |  Remark
-|-|-
Spring Cloud | Hoxton.SR3 | Spring Cloud |
Spring Boot | 2.2.5.RELEASE | Spring Boot |
spring-cloud-alibaba | 2.2.0.RELEASE | Spring Cloud Alibaba |
Spring Cloud Gateway | 2.2.5.RELEASE | Gateway |
Nacos | 1.2.1 | Alibaba Nacos Discovery/Config |
Sentinel | 1.7.2 | Alibaba Sentinel resilience and monitoring |
RocketMQ | 4.7.0 | Alibaba RocketMQ |
Seata | 1.1.0 | Alibaba distributed transaction solution |
Security | 2.2.1.RELEASE | Spring Cloud Security |
Spring Boot Admin | 2.2.2 | Spring Boot Admin |
Spring Boot Admin | 2.2.2 | Spring Boot Admin |
ELK | undetermined | Elasticsearch Logstash Kibana |
mybatisplus | 3.3.1 | Dao

## 文档资料参考：
### Spring Cloud
#### [Spring Cloud Docs](https://spring.io/projects/spring-cloud)
#### [Hoxton.SR3 Docs](https://cloud.spring.io/spring-cloud-static/Hoxton.SR3/reference/htmlsingle/)
### Spring Cloud Alibaba：
#### [spring-cloud-alibaba GITHUB](https://github.com/alibaba/spring-cloud-alibaba)
#### [spring-cloud-alibaba Docs](https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/zh-cn/index.html)


## Quick Start

### git clone
```shell script
git clone https://github.com/geekidea/spring-cloud-plus.git
mvn clean install
```

## 准备工作
### 1. Nacos 服务发现与配置
#### 下载Nacos 1.2.1
[https://github.com/alibaba/nacos/releases/tag/1.2.1](https://github.com/alibaba/nacos/releases/tag/1.2.1)

#### 启动Nacos
```shell script
sh nacos/bin/startup.sh -m standalone
```

#### 访问Nacos控制台
[http://localhost:8848/nacos](http://localhost:8848/nacos)
Nacos控制台账号密码：`nacos`/`nacos`

### 2. Sentinel 熔断/限流等
#### 下载Sentinel 1.7.2
[https://github.com/alibaba/Sentinel/releases/tag/1.7.2](https://github.com/alibaba/Sentinel/releases/tag/1.7.2)

#### 启动Sentinel
```shell script
java -Dserver.port=8600 -Dcsp.sentinel.dashboard.server=localhost:8600 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.7.2.jar
```

#### 访问Sentinel控制台
[http://localhost:8600](http://localhost:8600)
Sentinel控制台账号密码：`sentinel`/`sentinel`

### 3. seata
> 暂时不用配置
#### 下载seata
[https://github.com/seata/seata/releases/tag/v1.1.0](https://github.com/seata/seata/releases/tag/v1.1.0)


### 4. 服务启动顺序
```text
启动 scp-gateway 中的 ScpGatewayApplication
启动 scp-admin 中的 ScpAdminApplication
启动 scp-system 中的 ScpSystemServerApplication
启动 scp-example 中的 ScpExampleServerApplication
```

## 访问项目
#### Gateway 端口：9999

#### 访问 scp-admin 端口: 9000
> spring boot admin监控面板
[http://localhost:9000/wallboard](http://localhost:9000/wallboard)

#### 访问scp-system 端口: 9100
> 通过`gateway`方式访问

[http://localhost:9999/system/sysUser/info?name=hello](http://localhost:9999/system/sysUser/info?name=hello)

> 直接访问

[http://localhost:9100/sysUser/info?name=hello](http://localhost:9100/sysUser/info?name=hello)

#### 访问 scp-example 端口: 9200
> 通过`gateway`方式访问

[http://localhost:9999/example/example/test](http://localhost:9999/example/example/test)

> 直接访问

[http://localhost:9200/example/test](http://localhost:9200/example/test)

#### Fallback测试
> 通过`gateway`方式访问

[http://localhost:9999/example/example/testError](http://localhost:9999/example/example/testError)

> 直接访问

[http://localhost:9200/example/testError](http://localhost:9200/example/testError)
