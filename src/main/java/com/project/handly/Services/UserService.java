package com.project.handly.Services;

import com.project.handly.DTOs.User.LoginDTO;
import com.project.handly.DTOs.User.OauthDTO;
import com.project.handly.DTOs.User.UserDTO;
import com.project.handly.Entities.User;
import com.project.handly.Enum.Role;
import com.project.handly.Exceptions.GlobalExceptionHandler;
import com.project.handly.Mappers.OauthMapper;
import com.project.handly.Mappers.UserMapper;
import com.project.handly.Repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final OauthMapper oauthMapper;

    public UserService(UserRepo userRepo, UserMapper userMapper, PasswordEncoder passwordEncoder, OauthMapper oauthMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.oauthMapper = oauthMapper;
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public Optional<User> find(Long id) {
        return userRepo.findById(id);
    }

    public Optional<User> findFirstByEmail(String email) {
        return userRepo.findFirstByEmail(email);
    }

    public Optional<User> findFirstByPhone(String phone) {
        return userRepo.findFirstByPhone(phone);
    }


    public User findByPhone(String phone) {
        return userRepo.findByPhone(phone);
    }

    public List<User> all() {
        return userRepo.findAll();
    }

    public User register(UserDTO userDTO) {
        if (this.findFirstByEmail(userDTO.getEmail()).isPresent()) {
            throw new GlobalExceptionHandler.BadRequestException("Email already exists");
        }

        if (userDTO.getPhone() != null && this.findFirstByPhone(userDTO.getPhone()).isPresent()) {
            throw new GlobalExceptionHandler.BadRequestException("Phone already used");
        }

        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.user);
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

    public User registerOrGetGoogleUser(OauthDTO  oauthDTO) {
        User user = userRepo.findByEmail(oauthDTO.getEmail());
        if (user == null) {
            User newUser = oauthMapper.toEntity(oauthDTO);
            newUser.setRole(Role.user);
            String rawPassword = UUID.randomUUID().toString();
            newUser.setPassword(passwordEncoder.encode(rawPassword));
            LocalDateTime now = LocalDateTime.now();
            newUser.setVerifiedAt(now);
            userRepo.save(newUser);
            return newUser;
        }

        return user;
    }
}
