/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.test.mapper;

import com.tenhisi.web.test.entity.ExampleAsyncTreeEntity;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * example_tree Mapper接口
 * @author: Shane
 * @date 2021-12-21 11:03:28
 */
public interface ExampleAsyncTreeMapper extends BaseMapper<ExampleAsyncTreeEntity> {

    /**
     * 自定义分页查询
     * @param  page
     * @param  exampleAsyncTreeEntity 实体类
     */
    Page<ExampleAsyncTreeEntity> findPage(IPage<ExampleAsyncTreeEntity> page,
                                      @Param("exampleAsyncTree") ExampleAsyncTreeEntity exampleAsyncTreeEntity,
                                      @Param("sql_filter") String sql_filter);

    /**
     * 通过ID查询
     * @param id 查询ID
     * @return
     */
    ExampleAsyncTreeEntity findExampleAsyncTreeById(@Param("id") Long id);

    /**
     * 查询列表
     * @param exampleAsyncTreeEntity 查询条件对象
     * @return
     */
    List<ExampleAsyncTreeEntity> findList(ExampleAsyncTreeEntity exampleAsyncTreeEntity);

}
