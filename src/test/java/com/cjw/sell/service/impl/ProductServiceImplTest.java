package com.cjw.sell.service.impl;

import com.cjw.sell.entity.ProductInfo;
import com.cjw.sell.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @param <>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 17:59 2019-08-29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    @Test
    public void findUpAll() {
        Page<ProductInfo> upAll = productService.findUpAll(PageRequest.of(0, 2));
        System.out.println(upAll.getTotalElements());
    }

    @Test
    public void getOne() {
    }

    @Test
    public void testFindUpAll() {
    }

    @Test
    public void testFindUpAll1() {
    }

    @Test
    public void save() {
    }

    @Test
    public void increaseStock() {
    }

    @Test
    public void decreaseStock() {
    }
}
