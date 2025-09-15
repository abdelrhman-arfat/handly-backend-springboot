package com.project.handly.Services;

import com.project.handly.DTOs.LoginDTO;
import com.project.handly.DTOs.UserDTO;
import com.project.handly.Entities.User;
import com.project.handly.Enum.Role;
import com.project.handly.Exceptions.GlobalExceptionHandler;
import com.project.handly.Mappers.UserMapper;
import com.project.handly.Repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }


    public User findByPhone(String phone) {
        return userRepo.findByPhone(phone);
    }

    public List<User> all() {
        return userRepo.findAll();
    }

    public User register(UserDTO userDTO) {
        if (userRepo.findByEmail(userDTO.getEmail()) != null) {
            throw new GlobalExceptionHandler.BadRequestException("Email already exists");
        }

        if (userRepo.findByPhone(userDTO.getPhone()) != null) {
            throw new GlobalExceptionHandler.BadRequestException("Phone already used");
        }

        User user = userMapper.toEntity(userDTO);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        return userRepo.save(user);
    }

    public User login(LoginDTO loginDTO) {
        User user = userRepo.findByEmail(loginDTO.getEmail());
        if (user == null) {
            throw new GlobalExceptionHandler.NotFoundException("User not found");
        }

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new GlobalExceptionHandler.UnauthorizedException("Invalid credentials");
        }

        return user; // المستخدم متحقق
    }
}
