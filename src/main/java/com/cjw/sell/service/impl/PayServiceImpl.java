package com.cjw.sell.service.impl;

import com.cjw.sell.dto.OrderDto;
import com.cjw.sell.service.PayService;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @param <>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 17:21 2019-09-03
 */
@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private BestPayServiceImpl bestPayService;
    @Override
    public void create(OrderDto orderDto) {
        PayRequest payRequest = new PayRequest();
        bestPayService.pay(payRequest);
    }
}
