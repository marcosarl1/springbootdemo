package com.course.mapper;

import com.course.dto.CategoryDTO;
import com.course.model.Category;
import com.course.model.Product;
import com.course.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    private final ProductService productService;

    public CategoryMapper(ProductService productService) {
        this.productService = productService;
    }

    public CategoryDTO toDTO(Category category) {
        if (category == null) return null;

        return new CategoryDTO(category);
    }

    public List<CategoryDTO> toDTO(List<Category> categories) {
        return categories.stream()
                .map(this::toDTO)
                .toList();
    }

    public Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        if (categoryDTO.getProductsIds() != null && !categoryDTO.getProductsIds().isEmpty()) {
            for (Long productsId : categoryDTO.getProductsIds()) {
                Product product = productService.findById(productsId);
                category.getProducts().add(product);
            }
        }
        return category;
    }
}
