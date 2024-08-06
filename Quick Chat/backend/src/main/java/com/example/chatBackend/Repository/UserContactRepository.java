package com.example.chatBackend.Repository;

import com.example.chatBackend.Entity.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Long> {

    List<UserContact> findAllBySenderuser(String Senderuser);
}
