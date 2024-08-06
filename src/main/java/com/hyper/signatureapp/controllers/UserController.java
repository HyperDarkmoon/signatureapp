package com.hyper.signatureapp.controllers;

import com.hyper.signatureapp.models.User;
import com.hyper.signatureapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/{id}/signature")
    public User uploadSignature(@PathVariable int id, @RequestBody String base64Signature) {
        return userService.saveSignature(id, base64Signature);
    }

    @GetMapping("/{id}/signature")
    public String getSignature(@PathVariable int id) {
        return userService.getSignature(id);
    }
}
