package com.course.service;

import com.course.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();
    Order findById(Long id);
}
