package com.example.foobar.service;

import com.example.foobar.api.entity.FooBar;
import com.example.foobar.api.query.FooBarPageQuery;
import io.geekidea.cloud.common.core.service.BaseService;
import io.geekidea.cloud.common.core.pagination.Paging;

/**
 * FooBar 服务类
 *
 * @author geekidea
 * @since 2020-04-30
 */
public interface FooBarService extends BaseService<FooBar> {

    /**
     * 保存
     *
     * @param fooBar
     * @return
     * @throws Exception
     */
    boolean saveFooBar(FooBar fooBar) throws Exception;

    /**
     * 修改
     *
     * @param fooBar
     * @return
     * @throws Exception
     */
    boolean updateFooBar(FooBar fooBar) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteFooBar(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param fooBarQueryParam
     * @return
     * @throws Exception
     */
    Paging<FooBar> getFooBarPageList(FooBarPageQuery fooBarPageQuery) throws Exception;

}
