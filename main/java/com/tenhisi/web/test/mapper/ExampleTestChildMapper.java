/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.test.mapper;

import com.tenhisi.web.test.entity.ExampleTestChildEntity;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * example_test_child Mapper接口
 * @author: Shane
 * @date 2021-12-21 10:42:57
 */
public interface ExampleTestChildMapper extends BaseMapper<ExampleTestChildEntity> {

    /**
     * 自定义分页查询
     * @param  page
     * @param  exampleTestChildEntity 实体类
     */
    Page<ExampleTestChildEntity> findPage(IPage<ExampleTestChildEntity> page,
                                      @Param("exampleTestChild") ExampleTestChildEntity exampleTestChildEntity,
                                      @Param("sql_filter") String sql_filter);

    /**
     * 通过ID查询
     * @param id 查询ID
     * @return
     */
    ExampleTestChildEntity findExampleTestChildById(@Param("id") Long id);

    /**
     * 查询列表
     * @param exampleTestChildEntity 查询条件对象
     * @return
     */
    List<ExampleTestChildEntity> findList(ExampleTestChildEntity exampleTestChildEntity);

}
