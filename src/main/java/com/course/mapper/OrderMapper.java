package com.course.mapper;

import com.course.dto.OrderDTO;
import com.course.dto.PaymentDTO;
import com.course.model.Order;
import com.course.model.Payment;
import com.course.model.User;
import com.course.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    private final UserService userService;

    public OrderMapper(UserService userService) {
        this.userService = userService;
    }

    public OrderDTO toDto(Order order) {
        if (order == null) return null;

        OrderDTO dto = new OrderDTO(order);

        if (order.getPayment() != null) {
            PaymentDTO paymentDTO = new PaymentDTO(order.getPayment());
            dto.setPayment(paymentDTO);
        }

        return dto;
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
        order.setOrderStatus(orderDTO.getOrderStatus());

        if (orderDTO.getUserId() != null) {
            User user = userService.findById(orderDTO.getUserId());
            order.setClient(user);
        }

        if (orderDTO.getPayment() != null) {
            PaymentDTO paymentDTO = orderDTO.getPayment();
            Payment payment = new Payment();
            payment.setId(paymentDTO.getId());
            payment.setMoment(paymentDTO.getMoment());
            payment.setOrder(order);
            order.setPayment(payment);
        }

        return order;
    }
}
