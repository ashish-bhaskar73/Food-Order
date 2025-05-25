package com.foodOrder.FoodOrder.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryId;
    private int deliveryExecutiveId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryEstimateTime;

    private String deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders order;
}
