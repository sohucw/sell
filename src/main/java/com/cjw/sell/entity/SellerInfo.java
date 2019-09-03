package com.cjw.sell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 17:15 2019-08-29
 */
@Data
@Entity
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
