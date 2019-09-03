package com.cjw.sell.dao;

import com.cjw.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 20:47 2019-08-29
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster, String> {
    Page<OrderMaster> findByBuyerOpenid(String buyperOpenId, Pageable pageable);
}
