package com.hungpn.learn.springboot.controller;

import com.hungpn.learn.springboot.entity.User;
import com.hungpn.learn.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public Optional<User> getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<User> updateUserById (@PathVariable Long userId,@RequestBody User updateUserById){
        Optional<User> optinalUser=userRepository.findById(userId);
        if(optinalUser.isPresent()){
             User user =optinalUser.get();
             user.setActive(updateUserById.getActive());
             user.setFullName(updateUserById.getFullName());
             user.setBirthday(updateUserById.getBirthday());
             user.setUsername(updateUserById.getUsername());

             //luu vao database
            userRepository.save(user);
            return ResponseEntity.ok(user);
        }
        else {
            return ResponseEntity.notFound().build();
        }
      //  throw new ResourceNotFoundException("User not found with id " + userId);

    }
//
//
//    }
//
   @DeleteMapping("/users/{userId}")
   public ResponseEntity<User> deleteUserById (@PathVariable Long userId,@RequestBody User deleteUserById) {
       Optional<User> optinalUser = userRepository.findById(userId);
       if (optinalUser.isPresent() == true) {
           User user = optinalUser.get();
           userRepository.delete(user);

           return ResponseEntity.noContent().build();
       } else {
           return ResponseEntity.notFound().build();
       }
   }
}
