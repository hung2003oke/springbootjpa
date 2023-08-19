package com.hungpn.learn.springboot.controller;

import com.hungpn.learn.springboot.entity.Post;
import com.hungpn.learn.springboot.entity.User;
import com.hungpn.learn.springboot.exception.NotFoundException;
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
        Optional<Post> optionalPost= postRepository.findById((postId));
        if (optionalPost.isPresent()) {
            return postRepository.findById(postId);
        }
        else{
            throw new NotFoundException(" User with id "+postId +"notFound in DB !! Please check again .");
        }
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
            throw new NotFoundException("User with id : "+ userId+"not Found in DB !!! ,please check again ");
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
           throw new NotFoundException("Post with id : "+postId+"not found !!! , Please check again");
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
            throw new NotFoundException("Post with id :"+postId+" not found in DB !!! , Please check again ");
        }

    }

}
