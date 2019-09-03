package com.cjw.sell.service.impl;

import com.cjw.sell.dao.ProductInfoDao;
import com.cjw.sell.dto.CartDto;
import com.cjw.sell.entity.ProductInfo;
import com.cjw.sell.enums.ProductStatusEnum;
import com.cjw.sell.enums.ResultEnum;
import com.cjw.sell.exception.SellException;
import com.cjw.sell.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 17:18 2019-08-29
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoDao productInfoDao;
    @Override
    public ProductInfo getOne(String id) {
        return productInfoDao.getOne(id);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findUpAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDto> cartDtoList) {

    }

    @Override
    @Transient
    public void decreaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            ProductInfo productInfo = productInfoDao.getOne(cartDto.getProductId());
            if(productInfo == null) {
                throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int num = productInfo.getProductStock() - cartDto.getProductQuantity();
            if(num < 0) {
                throw  new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(num);
            productInfoDao.save(productInfo);
        }
    }
}
