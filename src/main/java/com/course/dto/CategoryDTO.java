package com.course.dto;

import com.course.model.Category;
import com.course.model.Product;

import java.util.Set;
import java.util.stream.Collectors;

public class CategoryDTO {

    private Long id;
    private String name;
    private Set<Long> productsIds;

    public CategoryDTO(Long id, String name, Set<Long> productsIds) {
        this.id = id;
        this.name = name;
        this.productsIds = productsIds;
    }

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.productsIds = category.getProducts()
                .stream()
                .map(Product::getId)
                .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(Set<Long> productsIds) {
        this.productsIds = productsIds;
    }
}
