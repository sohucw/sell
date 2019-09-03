package com.cjw.sell.dao;

import com.cjw.sell.entity.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 20:55 2019-08-29
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    @Transactional
    public void findByBuyerOpenid() {
        Page<OrderMaster> byBuyerOpenid = orderMasterDao.findByBuyerOpenid("110110", PageRequest.of(0, 1));
        System.out.println(byBuyerOpenid.getTotalElements());
    }
}