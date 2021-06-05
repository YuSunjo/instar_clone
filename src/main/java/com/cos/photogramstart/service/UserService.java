package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubScribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SubScribeRepository subScribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User updateUser(Long id, User user) {
        // 영속화
        User userEntity = userRepository.findById(id).orElseThrow(() -> {
            return new CustomValidationApiException("찾을 수 없는 id 입니다");
        });

        String rawPassword = user.getPassword();
        String encodePassword = bCryptPasswordEncoder.encode(rawPassword);
        userEntity.update(user, encodePassword);
        return userEntity;
        // 영속화된 오브젝트 수정 - 더티체킹 (업데이트 완료)
    }

    @Transactional
    public UserProfileDto profile(Long pageUserId, Long principalId) {

        UserProfileDto dto = new UserProfileDto();

        User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지는 없는 페이지 입니다.");
        });

        dto.setUser(userEntity);
        dto.setPageOwnerState(pageUserId.equals(principalId));   // 1은 페이지 주인, -1 은 주인이 아님
        dto.setImageCount(userEntity.getImages().size());

        int subscribeState = subScribeRepository.mSubscribeState(principalId, pageUserId);
        int subscribeCount = subScribeRepository.mSubscribeCount(pageUserId);

        dto.setSubscribeState(subscribeState == 1);
        dto.setSubscribeCount(subscribeCount);

        System.out.println("subscribeCount = " + subscribeCount);
        System.out.println("subscribeState = " + subscribeState);
        System.out.println("dto.getImageCount() = " + dto.getImageCount());
        return dto;
    }

}
