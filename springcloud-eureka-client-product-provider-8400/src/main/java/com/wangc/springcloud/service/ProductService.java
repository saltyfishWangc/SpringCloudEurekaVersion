package com.wangc.springcloud.service;

import com.wangc.springcloud.entity.Product;

/**
 * 商品服务类
 */
public interface ProductService {

    /**
     * 查询
     * @param id
     * @return
     */
    Product selectById(Long id);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 修改
     * @param name
     * @param id
     * @return
     */
    int updateById(String name, Long id);

    /**
     * 新增
     * @param product
     * @return
     */
    int insertOne(Product product);
}
