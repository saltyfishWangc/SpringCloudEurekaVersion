package com.wangc.springcloud.controller;

import com.wangc.springcloud.dto.Result;
import com.wangc.springcloud.entity.Product;
import com.wangc.springcloud.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class ProductProviderController {

    @Autowired
    private ProductService productService;

    /**
     * 查询
     * @param id
     * @return
     */
    @RequestMapping("product/provider/get/{id}")
    public Result selectById(@PathVariable("id") Long id) {
        log.info(id + "是我干的");
        return new Result(200, "查询成功", productService.selectById(id));
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("product/provider/delete/{id}")
    public Result deleteById(@PathVariable("id") Long id) {
        return new Result(200, "删除成功", productService.deleteById(id));
    }

    /**
     * 修改
     * @param product
     * @return
     */
    @RequestMapping("product/provider/update")
    public Result updateById(@RequestBody Product product) {
        return new Result(200, "修改成功", productService.updateById(product.getName(), product.getId()));
    }

    /**
     * 新增
     * @param product
     * @return
     */
    @RequestMapping("product/provider/add")
    public Result insertOne(@RequestBody Product product) {
        return new Result(200, "新增成功", productService.insertOne(product));
    }
}
