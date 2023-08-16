package com.hungpn.learn.springboot.controller;

import com.hungpn.learn.springboot.entity.Post;
import com.hungpn.learn.springboot.entity.User;
import com.hungpn.learn.springboot.repository.PostRepository;
import com.hungpn.learn.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PostController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts/{postId}")
    public Optional<Post> getPostById(@PathVariable Long postId) {
        return postRepository.findById(postId);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestParam("user_id") Long userId, @Valid @RequestBody Post post) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            post.setUser(user);

            Post savedPost = postRepository.save(post);
            return ResponseEntity.ok(savedPost);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

   @PutMapping("/posts/{postId}")
    public ResponseEntity<Post> updatePostById (@PathVariable Long postId, @RequestBody Post updatePostById) {
       Optional<Post> optionalPost = postRepository.findById(postId);
       if (optionalPost.isPresent()) {
           Post post = optionalPost.get();

           post.setId(updatePostById.getId());
           post.setContent(updatePostById.getContent());

           //luu vao database
           postRepository.save(post);
           return ResponseEntity.ok(post);
       } else {
           return ResponseEntity.notFound().build();
       }
    }


   @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Post> deletePostById (@PathVariable Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            postRepository.delete(post);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
