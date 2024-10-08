package com.example.back_end.repository;


import com.example.back_end.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);

   Boolean existsByEmail(String email);

}
