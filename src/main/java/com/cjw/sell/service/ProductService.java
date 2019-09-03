package com.cjw.sell.service;

import com.cjw.sell.dto.CartDto;
import com.cjw.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 17:15 2019-08-29
 */
public interface ProductService {
    ProductInfo getOne(String id);

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findUpAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    // 加库存
    void increaseStock(List<CartDto> cartDtoList);

    // 减少库存
    void decreaseStock(List<CartDto> cartDtoList);


}
