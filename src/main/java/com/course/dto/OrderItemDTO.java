package com.course.dto;

import com.course.model.Category;
import com.course.model.OrderItem;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderItemDTO {

    private Long productId;
    private String productName;
    private String productDescription;
    private Integer quantity;
    private Double price;
    private Set<String> categories = new HashSet<>();

    public OrderItemDTO() {}

    public OrderItemDTO(Long productId, String productName, String productDescription, Integer quantity, Double price) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItemDTO(OrderItem item) {
        this.productId = item.getProduct().getId();
        this.productName = item.getProduct().getName();
        this.productDescription = item.getProduct().getDescription();
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
        this.categories = item.getProduct().getCategories()
                .stream()
                .map(Category::getName)
                .collect(Collectors.toSet());
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public Double getSubTotal() {
        return price * quantity;
    }
}
