package com.example.back_end.repository;



import com.example.back_end.entity.Token;
import com.example.back_end.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;


public interface TokenRepository extends JpaRepository<Token,Integer> {
    @Modifying
    void deleteByUser(User user);

    Token findByKeyAndType(String key, Token.Type type);
}
