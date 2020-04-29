package com.example.foobar.api.feign;

import com.example.foobar.api.entity.FooBar;
import com.example.foobar.api.feign.factory.FooBarClientFallbackFactory;
import com.example.foobar.api.query.FooBarPageQuery;
import io.geekidea.cloud.common.core.api.ApiResult;
import io.geekidea.cloud.common.core.pagination.Paging;
import io.geekidea.cloud.framework.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * FooBar Feign Client
 *
 * @author geekidea
 * @since 2020-04-30
 */
@FeignClient(name = "scp-example", path = "/fooBar", fallbackFactory = FooBarClientFallbackFactory.class, configuration = FeignConfiguration.class)
public interface FooBarClient {

    /**
     * 添加FooBar
     */
    @PostMapping("/add")
    ApiResult<Boolean> addFooBar(@RequestBody FooBar fooBar) throws Exception;

    /**
     * 修改FooBar
     */
    @PostMapping("/update")
    ApiResult<Boolean> updateFooBar(@RequestBody FooBar fooBar) throws Exception;

    /**
     * 删除FooBar
     */
    ApiResult<Boolean> deleteFooBar(@PathVariable("id") Long id) throws Exception;

    /**
     * 获取FooBar详情
     */
    @GetMapping("/info/{id}")
    ApiResult<FooBar> getFooBar(@PathVariable("id") Long id) throws Exception;

    /**
     * FooBar分页列表
     */
    @PostMapping("/list")
    ApiResult<Paging<FooBar>> getFooBarPageList(@RequestBody FooBarPageQuery fooBarPageQuery) throws Exception;

}
