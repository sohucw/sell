package com.cjw.sell.dao;

import com.cjw.sell.entity.OrderDetail;
import com.cjw.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 20:47 2019-08-29
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId);
}
