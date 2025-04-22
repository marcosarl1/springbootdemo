package com.course.dto;

import com.course.model.Order;
import com.course.model.enums.OrderStatus;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus orderStatus;
    private Long userId;
    private Set<OrderItemDTO> items = new HashSet<>();
    private PaymentDTO payment;

    public OrderDTO() {}

    public OrderDTO(Long id, Instant moment, OrderStatus orderStatus, Long userId) {
        this.id = id;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.userId = userId;
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.moment = order.getMoment();
        this.orderStatus = order.getOrderStatus();;
        this.userId = order.getClient() != null ? order.getClient().getId() : null;
        this.items = order.getItems().stream()
                .map(OrderItemDTO::new)
                .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<OrderItemDTO> getItems() {
        return items;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO paymentDTO) {
        this.payment = paymentDTO;
    }

    public Double getTotal() {
        double sum = 0.0;
        for (OrderItemDTO item : items) {
            sum += item.getSubTotal();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderDTO orderDTO)) return false;
        return Objects.equals(id, orderDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
