package com.course.mapper;

import com.course.dto.UserDTO;
import com.course.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        if (user == null) return null;

        return new UserDTO(user);
    }

    public List<UserDTO> toDTO(List<User> users) {
        if (users == null) return null;

        return users.stream()
                .map(this::toDTO)
                .toList();
    }

    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) return null;

        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword());

        return user;
    }
}
