package com.example.chatBackend.Repository;

import com.example.chatBackend.Entity.UserContact;
import com.example.chatBackend.Entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserStatusRepository extends JpaRepository<UserStatus, String> {

    UserStatus findByUsername(String username);
//    List<UserStatus> findAllByOnline(boolean online);
//    List<UserStatus> findAll();
}