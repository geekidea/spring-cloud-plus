package com.example.foobar.controller;

import com.example.foobar.api.entity.FooBar;
import com.example.foobar.service.FooBarService;
import lombok.extern.slf4j.Slf4j;
import com.example.foobar.api.query.FooBarPageQuery;
import io.geekidea.cloud.common.core.controller.BaseController;
import io.geekidea.cloud.common.core.api.ApiResult;
import io.geekidea.cloud.common.core.pagination.Paging;
import io.geekidea.cloud.common.core.validator.groups.Add;
import io.geekidea.cloud.common.core.validator.groups.Update;
import org.springframework.validation.annotation.Validated;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * FooBar 控制器
 *
 * @author geekidea
 * @since 2020-04-30
 */
@Slf4j
@RestController
@RequestMapping("/fooBar")
@Api(value = "FooBarAPI", tags = {"FooBar"})

public class FooBarController extends BaseController {

    @Autowired
    private FooBarService fooBarService;

    /**
     * 添加FooBar
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加FooBar", response = ApiResult.class)
    public ApiResult<Boolean> addFooBar(@Validated(Add.class) @RequestBody FooBar fooBar) throws Exception {
        boolean flag = fooBarService.saveFooBar(fooBar);
        return ApiResult.result(flag);
    }

    /**
     * 修改FooBar
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改FooBar", response = ApiResult.class)
    public ApiResult<Boolean> updateFooBar(@Validated(Update.class) @RequestBody FooBar fooBar) throws Exception {
        boolean flag = fooBarService.updateFooBar(fooBar);
        return ApiResult.result(flag);
    }

    /**
     * 删除FooBar
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除FooBar", response = ApiResult.class)
    public ApiResult<Boolean> deleteFooBar(@PathVariable("id") Long id) throws Exception {
        boolean flag = fooBarService.deleteFooBar(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取FooBar详情
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "FooBar详情", response = FooBar.class)
    public ApiResult<FooBar> getFooBar(@PathVariable("id") Long id) throws Exception {
        FooBar fooBar = fooBarService.getById(id);
        return ApiResult.ok(fooBar);
    }

    /**
     * FooBar分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "FooBar分页列表", response = FooBar.class)
    public ApiResult<Paging<FooBar>> getFooBarPageList(@Validated @RequestBody FooBarPageQuery fooBarPageQuery) throws Exception {
        Paging<FooBar> paging = fooBarService.getFooBarPageList(fooBarPageQuery);
        return ApiResult.ok(paging);
    }

}
