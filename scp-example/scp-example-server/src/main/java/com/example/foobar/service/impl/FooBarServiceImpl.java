package com.example.foobar.service.impl;

import com.example.foobar.api.entity.FooBar;
import com.example.foobar.mapper.FooBarMapper;
import com.example.foobar.service.FooBarService;
import com.example.foobar.api.query.FooBarPageQuery;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.geekidea.cloud.common.core.service.impl.BaseServiceImpl;
import io.geekidea.cloud.common.core.pagination.Paging;
import io.geekidea.cloud.common.core.pagination.PageInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FooBar 服务实现类
 *
 * @author geekidea
 * @since 2020-04-30
 */
@Slf4j
@Service
public class FooBarServiceImpl extends BaseServiceImpl<FooBarMapper, FooBar> implements FooBarService {

    @Autowired
    private FooBarMapper fooBarMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveFooBar(FooBar fooBar) throws Exception {
        return super.save(fooBar);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateFooBar(FooBar fooBar) throws Exception {
        return super.updateById(fooBar);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteFooBar(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public Paging<FooBar> getFooBarPageList(FooBarPageQuery fooBarPageQuery) throws Exception {
        Page<FooBar> page = new PageInfo<>(fooBarPageQuery, OrderItem.desc(getLambdaColumn(FooBar::getCreateTime)));
        LambdaQueryWrapper<FooBar> wrapper = new LambdaQueryWrapper<>();
        IPage<FooBar> iPage = fooBarMapper.selectPage(page, wrapper);
        return new Paging<FooBar>(iPage);
    }

}
