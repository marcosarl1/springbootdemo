package com.course.service;

import com.course.model.Category;
import com.course.model.User;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
}
