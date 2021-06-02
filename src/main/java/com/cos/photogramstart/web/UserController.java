package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public String profile(@PathVariable Long id, Model model) {
        User userEntity = userService.profile(id);
        model.addAttribute("user", userEntity);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        // 밑에 두줄을 어노테이션으로 제공  - 사용 X
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        authentication.getPrincipal();
        System.out.println("principalDetails = " + principalDetails.getUser().getId());
        System.out.println("principalDetails = " + principalDetails.getUser().getEmail());
        System.out.println("principalDetails = " + principalDetails.getUser().getPassword());
        // 이렇게 안하고 header 에서 시큐리티 설정 해줌
//        model.addAttribute("principal", principalDetails.getUser());
        return "user/update";
    }

}

