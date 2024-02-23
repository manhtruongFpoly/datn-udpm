package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private Long productPrice;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String orderCode;
    private Integer shipping;
    private String description;
    private String reson;
    private Integer status;
    private Integer orderStatus;
    private Long roleId;
    private Long grandTotal;
    private Integer discount;
    private Long userId;
    private Long paymentId;


}
