package com.hungpn.learn.springboot.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
        @NotBlank(message = "Username is required")
        @Size(min = 5, max = 20, message = "Username length must be between 5 and 20 characters")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must contain only letters and numbers")
        private String username ;
        private Long id;
        private String gender;

        public UserDto(){
        }

        public UserDto(String username, String gender, Long id ){
             this.gender=gender;
            this.username=username;
            this.id=id;
        }

        public String getUsername(){
            return username;
        }
        public void setUsername(String username){
            this.username=username;
        }
        public Long getId(){
        return id;
        }
        public void setId(Long id){
           this.id=id;
        }
        public String getGender(){
           return gender;
        }
        public void setGender(String gender){
           this.gender=gender;
        }
}
 //    public String get


