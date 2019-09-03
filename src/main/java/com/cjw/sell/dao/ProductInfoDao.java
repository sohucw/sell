package com.cjw.sell.dao;

import com.cjw.sell.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 17:11 2019-08-29
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo, String> {
    /**
     * 通过产品状态查找产品
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
