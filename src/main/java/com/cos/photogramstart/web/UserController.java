package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @GetMapping("/user/{id}")
    public String profile(@PathVariable Long id) {
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        // 밑에 두줄을 어노테이션으로 제공  - 사용 X
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        authentication.getPrincipal();


        return "user/update";
    }

}

