package com.cos.photogramstart.domain.user;


import com.cos.photogramstart.domain.image.Image;
import lombok.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    // User 를 select할 때 해당 user id로 등록된 image 가져온다. lazy -> getImages 를 부를 때 가져온다.
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) // 나는 연관관계의 주인이 아니다. 페이지의 컬럼 만들지 않는다.
    private List<Image> images;

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    public void update(User user, String encodePassword) {
        this.name = user.getName();
        this.password = encodePassword;
        this.bio = user.getBio();
        this.website = user.getWebsite();
        this.phone = user.getPhone();
        this.gender = user.getGender();
    }

}
