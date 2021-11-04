/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.test.mapper;

import com.tenhisi.web.test.entity.ExampleSynchTreeEntity;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * example_tree Mapper接口
 * @author: Shane
 * @date 2021-12-21 10:58:12
 */
public interface ExampleSynchTreeMapper extends BaseMapper<ExampleSynchTreeEntity> {

    /**
     * 通过ID查询
     * @param id 查询ID
     * @return
     */
    ExampleSynchTreeEntity findExampleSynchTreeById(@Param("id") Long id);

    /**
     * 查询列表
     * @param exampleSynchTreeEntity 查询条件对象
     * @return
     */
    List<ExampleSynchTreeEntity> findList(ExampleSynchTreeEntity exampleSynchTreeEntity);

    /**
     * @param exampleSynchTreeEntity  查询条件对象
     * @param sidx 排序字段
     * @param asc 排序方式
     * @param sql_filter 权限过滤
     * @return
     */
    List<ExampleSynchTreeEntity> findTreeList(@Param("exampleSynchTree") ExampleSynchTreeEntity exampleSynchTreeEntity,
                                              @Param("sidx") String sidx,
                                              @Param("order") String asc,
                                              @Param("sql_filter") String sql_filter);

}
