package com.cos.photogramstart.domain.user;


import lombok.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String website;

    private String bio;   //자기 소개

    @Column(nullable = false)
    private String email;

    private String phone;

    private String gender;

    private String profileImageUrl;

    private String role;

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }


}
