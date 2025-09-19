package com.project.handly.Repositories;

import com.project.handly.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByPhone(String phone);
    Optional<User> findFirstByEmail(String email);
    Optional<User> findFirstByPhone(String phone);

}
