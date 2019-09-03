package com.cjw.sell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 15:45 2019-08-29
 */
@Data
@Entity
@Table(name = "product_category")
@DynamicUpdate // 动态更新时间
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column
    private String categoryName;
    @Column
    private Integer categoryType;
    @Column
    private Date createTime;
    @Column
    private Date updateTime;
}
