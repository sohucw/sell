package com.cjw.sell.controller;

import com.cjw.sell.converter.OrderForm2OrderDto;
import com.cjw.sell.dto.OrderDto;
import com.cjw.sell.enums.ResultEnum;
import com.cjw.sell.exception.SellException;
import com.cjw.sell.form.OrderForm;
import com.cjw.sell.service.BuyerService;
import com.cjw.sell.service.OrderService;
import com.cjw.sell.utils.ResultVoUtil;
import com.cjw.sell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param <>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 10:32 2019-09-03
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    // 创建订单

    @PostMapping("/create")
    public ResultVo<Map<String ,String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PRAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderForm2OrderDto.convert(orderForm);

        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("创建订单 购物车为null = {}", orderDto.getOrderDetailList());
            throw  new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDto order = orderService.createOrder(orderDto);
        Map<String ,String> map = new HashMap<>();
        map.put("orderId", order.getOrderId());
        return ResultVoUtil.success(map);
    }

    // 订单list
    @GetMapping("/list")
    public ResultVo<List<OrderDto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if(StringUtils.isEmpty(openid)) {
            log.error("[查询订单list]openid为null");
            throw new SellException(ResultEnum.PRAM_ERROR);
        }
        Page<OrderDto> list = orderService.findList(openid, PageRequest.of(page, size));

        return ResultVoUtil.success(list.getContent());
    }

    // 订单详情

    @GetMapping("/detail")
    public ResultVo<OrderDto> detail (@RequestParam("openid") String openid,
                                      @RequestParam(value = "orderId") String orderId) {
        OrderDto orderDto = buyerService.findOrderOne(openid, orderId);

        return ResultVoUtil.success(orderDto);
    }

    // 取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid,
                           @RequestParam(value = "orderId") String orderId) {

        buyerService.cancelOrder(openid, orderId);
        return ResultVoUtil.success();
    }

    @GetMapping("/test")
    public ResultVo test() {
        return ResultVoUtil.success();
    }

}
