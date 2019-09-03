package com.cjw.sell.service.impl;

import com.cjw.sell.dao.ProductCategoryDao;
import com.cjw.sell.entity.ProductCategory;
import com.cjw.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 16:59 2019-08-29
 */
@Service
public class CategoryServiceImpl  implements CategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Override
    public ProductCategory findOne(Integer id) {
        return productCategoryDao.getOne(id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> ids) {
        return productCategoryDao.findByCategoryTypeIn(ids);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);

    }
}
