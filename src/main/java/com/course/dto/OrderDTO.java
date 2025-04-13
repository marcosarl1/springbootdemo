package com.course.dto;

import com.course.model.Order;

import java.time.Instant;
import java.util.Objects;

public class OrderDTO {

    private Long id;
    private Instant moment;
    private Long userId;

    public OrderDTO() {}

    public OrderDTO(Long id, Instant moment, Long userId) {
        this.id = id;
        this.moment = moment;
        this.userId = userId;
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.moment = order.getMoment();
        this.userId = order.getClient() != null ? order.getClient().getId() : null;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
