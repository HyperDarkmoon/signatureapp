package com.hyper.signatureapp.services;

import com.hyper.signatureapp.models.User;
import com.hyper.signatureapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User saveSignature(int id, String base64Signature) {
        byte[] signatureBytes = Base64.getDecoder().decode(base64Signature);
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setSignature(signatureBytes);
        return userRepository.save(user);
    }

    public String getSignature(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return Base64.getEncoder().encodeToString(user.getSignature());
    }
}
