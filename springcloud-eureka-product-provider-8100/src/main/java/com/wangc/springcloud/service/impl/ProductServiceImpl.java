package com.wangc.springcloud.service.impl;

import com.wangc.springcloud.dao.ProductMapper;
import com.wangc.springcloud.entity.Product;
import com.wangc.springcloud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    public Product selectById(Long id) {
        return productMapper.selectById(id);
    }

    public int deleteById(Long id) {
        return productMapper.deleteById(id);
    }

    public int updateById(String name, Long id) {
        return productMapper.updateById(name, id);
    }

    public int insertOne(Product product) {
        return productMapper.insertOne(product);
    }
}
