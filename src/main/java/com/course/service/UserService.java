package com.course.service;

import com.course.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(Long id);
}
