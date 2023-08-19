package com.hungpn.learn.springboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PostDto {
    private Long id;
    private  String title;

    public PostDto(){
    }

    public PostDto(Long id, String title){
        this.id=id;
        this.title=title;
    }
    public Long getId(){

        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
}
