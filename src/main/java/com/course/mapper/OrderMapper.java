package com.course.mapper;

import com.course.dto.OrderDTO;
import com.course.model.Order;
import com.course.model.User;
import com.course.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final UserService userService;

    public OrderMapper(UserService userService) {
        this.userService = userService;
    }

    public OrderDTO toDto(Order order) {
        if (order == null) return null;

        return new OrderDTO(order);
    }

    public List<OrderDTO> toDTO(List<Order> orders) {
        return orders.stream()
                .map(this::toDto)
                .toList();
    }

    public Order toEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setMoment(orderDTO.getMoment());

        if (orderDTO.getUserId() != null) {
            User user = userService.findById(orderDTO.getUserId());
            order.setClient(user);
        }

        return order;
    }
}
