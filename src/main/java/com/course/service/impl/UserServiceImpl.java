package com.course.service.impl;

import com.course.exception.DatabaseException;
import com.course.exception.ResourceNotFoundException;
import com.course.model.User;
import com.course.repository.UserRepository;
import com.course.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        try {
            User existingUser = userRepository.getReferenceById(id);
            updateData(existingUser, user);
            return userRepository.save(existingUser);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            User user = userRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
            userRepository.delete(user);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(User existingUser, User user) {
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
    }
}
