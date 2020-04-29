package com.example.foobar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.foobar.api.entity.FooBar;
import com.example.foobar.api.query.FooBarPageQuery;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.io.Serializable;

/**
 * FooBar Mapper 接口
 *
 * @author geekidea
 * @since 2020-04-30
 */
@Repository
public interface FooBarMapper extends BaseMapper<FooBar> {


}
