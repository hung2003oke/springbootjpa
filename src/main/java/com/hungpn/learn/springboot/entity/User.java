package com.hungpn.learn.springboot.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.hungpn.learn.springboot.model.AuditModel;

import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 25)
    private String username;

    @Column(name = "full_name")
    private String fullName;

    private Boolean active;

    private String gender;
    private Date birthday;
}
