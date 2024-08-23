package com.hyper.signatureapp.repositories;

import com.hyper.signatureapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
    public User findByEmail(String email);
    public User findByPhone(String phone);
    public User findByIdCard(String idCard);
}
