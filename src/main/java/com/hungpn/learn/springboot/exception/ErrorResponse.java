package com.hungpn.learn.springboot.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor

public class ErrorResponse{
     private HttpStatus status;
     private String message;
     private String message2;
     public ErrorResponse(HttpStatus status,String message ){
          this.status=status;
          this.message2=message2;
          this.message=message;
     }
}
