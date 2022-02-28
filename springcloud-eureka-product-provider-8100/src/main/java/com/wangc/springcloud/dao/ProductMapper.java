package com.wangc.springcloud.dao;

import com.wangc.springcloud.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper {

    /**
     * 查询
     * @param id
     * @return
     */
    Product selectById(@Param("id") Long id);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(@Param("id") Long id);

    /**
     * 修改
     * @param name
     * @param id
     * @return
     */
    int updateById(@Param("name") String name, @Param("id") Long id);

    /**
     * 新增
     * @param product
     * @return
     */
    int insertOne(Product product);
}
