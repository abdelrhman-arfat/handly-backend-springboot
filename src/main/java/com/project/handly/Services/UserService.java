package com.project.handly.Services;

<<<<<<< HEAD
import com.project.handly.DTOs.User.LoginDTO;
import com.project.handly.DTOs.User.OauthDTO;
import com.project.handly.DTOs.User.UserDTO;
import com.project.handly.Entities.User;
import com.project.handly.Enum.Role;
import com.project.handly.Exceptions.GlobalExceptionHandler;
import com.project.handly.Mappers.OauthMapper;
=======
import com.project.handly.DTOs.LoginDTO;
import com.project.handly.DTOs.UserDTO;
import com.project.handly.Entities.User;
import com.project.handly.Enum.Role;
import com.project.handly.Exceptions.GlobalExceptionHandler;
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
import com.project.handly.Mappers.UserMapper;
import com.project.handly.Repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
=======
import java.util.List;
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb

@Service
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
<<<<<<< HEAD
    private final OauthMapper oauthMapper;

    public UserService(UserRepo userRepo, UserMapper userMapper, PasswordEncoder passwordEncoder, OauthMapper oauthMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.oauthMapper = oauthMapper;
=======

    public UserService(UserRepo userRepo, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

<<<<<<< HEAD
    public Optional<User> find(Long id) {
        return userRepo.findById(id);
    }

    public Optional<User> findFirstByEmail(String email) {
        return userRepo.findFirstByEmail(email);
    }

    public Optional<User> findFirstByPhone(String phone) {
        return userRepo.findFirstByPhone(phone);
    }

=======
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb

    public User findByPhone(String phone) {
        return userRepo.findByPhone(phone);
    }

    public List<User> all() {
        return userRepo.findAll();
    }

    public User register(UserDTO userDTO) {
<<<<<<< HEAD
        if (this.findFirstByEmail(userDTO.getEmail()).isPresent()) {
            throw new GlobalExceptionHandler.BadRequestException("Email already exists");
        }

        if (userDTO.getPhone() != null && this.findFirstByPhone(userDTO.getPhone()).isPresent()) {
=======
        if (userRepo.findByEmail(userDTO.getEmail()) != null) {
            throw new GlobalExceptionHandler.BadRequestException("Email already exists");
        }

        if (userRepo.findByPhone(userDTO.getPhone()) != null) {
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
            throw new GlobalExceptionHandler.BadRequestException("Phone already used");
        }

        User user = userMapper.toEntity(userDTO);
<<<<<<< HEAD
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.user);
=======

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
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
<<<<<<< HEAD

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
=======
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
}
