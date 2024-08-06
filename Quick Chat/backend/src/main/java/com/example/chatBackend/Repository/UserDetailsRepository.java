package com.example.chatBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.chatBackend.Entity.UserDetails;

import java.util.List;

public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {
    UserDetails findByUserName(String userName);
    List<UserDetails> findAllByUserNameNot(String userName);
}

