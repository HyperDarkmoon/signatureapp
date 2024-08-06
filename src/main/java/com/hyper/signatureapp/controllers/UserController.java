package com.hyper.signatureapp.controllers;

import com.hyper.signatureapp.models.User;
import com.hyper.signatureapp.repositories.UserRepository;
import com.hyper.signatureapp.services.UserService;
import com.hyper.signatureapp.models.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(
            @Valid @RequestBody RegisterDto registerDto,
            BindingResult result) {
        if (result.hasErrors()) {
            var errorsList = result.getAllErrors();
            var errorsMap = new HashMap<String, String>();
            for (int i = 0; i<errorsList.size(); i++) {
                var error = (FieldError) errorsList.get(i);
                errorsMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorsMap);
        }
        var bCryptEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(bCryptEncoder.encode(registerDto.getPassword()));
        user.setSignature(registerDto.getSignature());

        try {
            var otherUser = userRepository.findByUsername(registerDto.getUsername());
            if (otherUser != null) {
                return ResponseEntity.badRequest().body("Username already exists");
            }
            otherUser = userRepository.findByEmail(registerDto.getEmail());
            if (otherUser != null) {
                return ResponseEntity.badRequest().body("Email already exists");
            }

            userRepository.save(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            System.out.println("There is an exception : ");
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().body("Error saving user");
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
