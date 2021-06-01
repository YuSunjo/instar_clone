package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {

    @NotBlank
    private String name;

    @NotBlank
    private String password;
    private String website;
    private String bio;
    private String phone;
    private String gender;

    // 코드 수정이 필요함
    public User toEntity() {
        return User.builder()
                .name(name)
                .password(password)   //패스워드를 안넣었으면 공백  문제 발생
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }

}
