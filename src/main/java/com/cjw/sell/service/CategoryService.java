package com.cjw.sell.service;

import com.cjw.sell.entity.ProductCategory;

import java.util.List;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 16:56 2019-08-29
 */


public interface CategoryService {
    ProductCategory findOne(Integer id);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> ids);
    ProductCategory save(ProductCategory productCategory);
}
