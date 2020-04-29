package com.example.foobar.api.feign.fallback;

import com.example.foobar.api.entity.FooBar;
import com.example.foobar.api.feign.FooBarClient;
import com.example.foobar.api.query.FooBarPageQuery;
import io.geekidea.cloud.common.core.api.ApiResult;
import io.geekidea.cloud.common.core.pagination.Paging;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * FooBar Feign Fallback
 *
 * @author geekidea
 * @since 2020-04-30
 */
@Slf4j
@Component
public class FooBarClientFallback implements FooBarClient {

    @Setter
    private Throwable throwable;

    /**
     * 添加FooBar
     */
    @Override
    public ApiResult<Boolean> addFooBar(FooBar fooBar) throws Exception {
        log.error("错误原因：", throwable);
        return ApiResult.fail();
    }

    /**
     * 修改FooBar
     */
    @Override
    public ApiResult<Boolean> updateFooBar(FooBar fooBar) throws Exception {
        log.error("错误原因：", throwable);
        return ApiResult.fail();
    }

    /**
     * 删除FooBar
     */
    @Override
    public ApiResult<Boolean> deleteFooBar(Long id) throws Exception {
        log.error("错误原因：", throwable);
        return ApiResult.fail();
    }

    /**
     * 获取FooBar详情
     */
    @Override
    public ApiResult<FooBar> getFooBar(Long id) throws Exception {
        log.error("错误原因：", throwable);
        return ApiResult.fail();
    }

    /**
     * FooBar分页列表
     */
    @Override
    public ApiResult<Paging<FooBar>> getFooBarPageList(FooBarPageQuery fooBarPageQuery) throws Exception {
        log.error("错误原因：", throwable);
        return ApiResult.fail();
    }
}
