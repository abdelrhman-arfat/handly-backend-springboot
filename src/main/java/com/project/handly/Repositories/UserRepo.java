package com.project.handly.Repositories;

import com.project.handly.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByPhone(String phone);
<<<<<<< HEAD
    Optional<User> findFirstByEmail(String email);
    Optional<User> findFirstByPhone(String phone);


=======
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
}
