package com.hungpn.learn.springboot.service;

import com.hungpn.learn.springboot.dto.UserDto;
import com.hungpn.learn.springboot.entity.User;
import com.hungpn.learn.springboot.exception.InvalidException;
import com.hungpn.learn.springboot.exception.InvalidException;
import com.hungpn.learn.springboot.exception.NotFoundException;
import com.hungpn.learn.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        validateUserId(userId);
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Can't find User with Id: " + userId + ". Please check again!!!"));
    }

    public User createUser(UserDto userDto) {
        validateUserDto(userDto);

        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setId(userDto.getId());
        newUser.setGender(userDto.getGender());

        return userRepository.save(newUser);
    }

    public User updateUser(UserDto userDto, Long userId) {
        validateUserId(userId);
        validateUserDto(userDto);

        User existingUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found exception "));
        existingUser.setId(userDto.getId());
        existingUser.setGender(userDto.getGender());
        existingUser.setUsername(userDto.getUsername());

        return userRepository.save(existingUser);
    }

    public void deleteUserById(Long userId) {
        validateUserId(userId);
        userRepository.deleteById(userId);
    }

    private void validateUserId(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidException("Invalid User with id " + id);
        }
    }

    private void validateUserDto(UserDto userDto) {
        if (!isValidGender(userDto.getGender())) {
            throw new InvalidException("Invalid Gender");
        }

        if (!isValidUsername(userDto.getUsername())) {
            throw new InvalidException("Invalid Username");
        }
    }

    private boolean isValidGender(String gender) {
        return "Male".equals(gender) || "Female".equals(gender) || "Bisexual".equals(gender);
    }

    private boolean isValidUsername(String username) {
        // Kiểm tra tính hợp lệ của tên người dùng, sử dụng các annotation @NotBlank, @Size, @Pattern
        return true;
    }
}

