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
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(
            @Valid @RequestBody RegisterDto registerDto,
            BindingResult result) {

        logger.info("Received registration request for username: {}", registerDto.getUsername());

        if (result.hasErrors()) {
            Map<String, String> errorsMap = new HashMap<>();
            result.getAllErrors().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            logger.error("Validation errors: {}", errorsMap);
            return ResponseEntity.badRequest().body(errorsMap);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(encoder.encode(registerDto.getPassword()));
        user.setSignature(registerDto.getSignature());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setPhone(registerDto.getPhone());
        user.setIdCard(registerDto.getIdCard());
        user.setAddress(registerDto.getAddress());
        user.setOffer(registerDto.getOffer());

        try {
            if (userRepository.findByUsername(registerDto.getUsername()) != null) {
                logger.warn("Username already exists: {}", registerDto.getUsername());
                return ResponseEntity.badRequest().body("Username already exists");
            }
            if (userRepository.findByEmail(registerDto.getEmail()) != null) {
                logger.warn("Email already exists: {}", registerDto.getEmail());
                return ResponseEntity.badRequest().body("Email already exists");
            }

            userRepository.save(user);
            logger.info("User successfully registered: {}", registerDto.getUsername());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            logger.error("Error saving user", e);
            return ResponseEntity.status(500).body("Error saving user");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            logger.info("User not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/signature")
    public ResponseEntity<User> uploadSignature(
            @PathVariable int id,
            @RequestBody String base64Signature) {
        try {
            User updatedUser = userService.saveSignature(id, base64Signature);
            logger.info("Signature uploaded for user id: {}", id);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            logger.error("Error uploading signature for user id: {}", id, e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}/signature")
    public ResponseEntity<String> getSignature(@PathVariable int id) {
        try {
            String signature = userService.getSignature(id);
            if (signature != null) {
                logger.info("Signature retrieved for user id: {}", id);
                return ResponseEntity.ok(signature);
            } else {
                logger.info("No signature found for user id: {}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error retrieving signature for user id: {}", id, e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/check")
    public ResponseEntity<Map<String, Boolean>> checkUserExistence(
            @RequestParam String username,
            @RequestParam String email) {
        logger.info("Checking existence for username: {} and email: {}", username, email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("usernameExists", userRepository.findByUsername(username) != null);
        response.put("emailExists", userRepository.findByEmail(email) != null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/infocheck")
    public ResponseEntity<Map<String, Boolean>> checkUserInfo(
            @RequestParam String phone,
            @RequestParam String idCard) {
        logger.info("Checking existence for phone: {} and idCard: {}", phone, idCard);
        Map<String, Boolean> response = new HashMap<>();
        response.put("phoneExists", userRepository.findByPhone(phone) != null);
        response.put("idCardExists", userRepository.findByIdCard(idCard) != null);
        return ResponseEntity.ok(response);
    }
}
