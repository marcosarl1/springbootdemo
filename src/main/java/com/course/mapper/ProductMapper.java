package com.course.mapper;

import com.course.dto.ProductDTO;
import com.course.model.Category;
import com.course.model.Product;
import com.course.service.CategoryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    private final CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ProductDTO toDTO(Product product) {
        if (product == null) return null;

        return new ProductDTO(product);
    }

    public List<ProductDTO> toDTO(List<Product> products) {
        return products.stream()
                .map(this::toDTO)
                .toList();
    }

    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(product.getImgUrl());
        if (productDTO.getCategories_Id() != null && !productDTO.getCategories_Id().isEmpty()) {
            for (Long categoryId : productDTO.getCategories_Id()) {
                Category category = categoryService.findById(categoryId);
                if (category != null) {
                    product.getCategories().add(category);
                }
            }
        }
        return product;
    }
}
