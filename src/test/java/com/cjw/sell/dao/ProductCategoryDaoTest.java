package com.cjw.sell.dao;

import com.cjw.sell.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 16:03 2019-08-29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    @Transactional
    public void findOneTest() {
        ProductCategory productCategory = productCategoryDao.getOne(2);
        System.out.println(productCategory.getCategoryName());
    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("水饺");
        productCategory.setCategoryType(3);
        productCategoryDao.save(productCategory);
        System.out.println(productCategory.getCategoryName());
    }

    @Test
    @Transactional
    public void findByCategoryTypeInTest() {
        List<ProductCategory> byCategoryTypeIn = productCategoryDao.findByCategoryTypeIn(Arrays.asList(1, 2, 3));
        System.out.println(byCategoryTypeIn.size());
    }
}
