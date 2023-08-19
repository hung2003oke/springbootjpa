package com.hungpn.learn.springboot.service;

import com.hungpn.learn.springboot.dto.PostDto;
import com.hungpn.learn.springboot.dto.UserDto;
import com.hungpn.learn.springboot.entity.Post;
import com.hungpn.learn.springboot.entity.Post;
import com.hungpn.learn.springboot.exception.InvalidException;
import com.hungpn.learn.springboot.exception.NotFoundException;
import com.hungpn.learn.springboot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post getUserById(Long userId) {
        validatePostId(userId);
        return postRepository.findById(userId).orElseThrow(() -> new NotFoundException("Can't find User with Id: " + userId + ". Please check again!!!"));
    }

    public Post createUser(PostDto postDto) {
        validatePostDto(postDto);

        Post newUser = new Post();
        newUser.setTitle(PostDto.getTitle());
        newUser.setId(postDto.getId());


        return postRepository.save(newUser);
    }

    public Post updatePost(PostDto postDto, Long postId) {
        validatePostId(postId);
        validatePostDto(postDto);

        Post existingPost = postRepository.findById(postId).orElseThrow(() -> new NotFoundException("User not found exception "));
        existingPost.setId(postDto.getId());
        existingPost.setTitle(postDto.getTitle());

        return postRepository.save(existingPost);
    }
    private void validatePostId(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidException("Invalid User with id " + id);
        }
    }


    private boolean isValidTitle(String title) {
        // Kiểm tra tính hợp lệ của tên người dùng, sử dụng các annotation @NotBlank, @Size, @Pattern
        return true;
    }

    }


}
