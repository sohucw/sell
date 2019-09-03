package com.cjw.sell.controller;

import com.cjw.sell.entity.ProductCategory;
import com.cjw.sell.entity.ProductInfo;
import com.cjw.sell.service.CategoryService;
import com.cjw.sell.service.ProductService;
import com.cjw.sell.utils.ResultVoUtil;
import com.cjw.sell.vo.ProductInfoVo;
import com.cjw.sell.vo.ProductVo;
import com.cjw.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @param <>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 19:43 2019-08-29
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ResultVo list() {
        // 查询所有上架商品
        List<ProductInfo> upAll = productService.findUpAll();
        // 查询类目 一次性查询
        List<Integer> categoryTypeList = upAll.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        // 类目的list
        List<ProductCategory> byCategoryTypeIn = categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVo> productVoList = new ArrayList<>();
        // 数据拼装
        for(ProductCategory productCategory: byCategoryTypeIn) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for(ProductInfo productInfo: upAll) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);

                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
        return ResultVoUtil.success(productVoList);
    }

}
