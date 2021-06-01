package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubScribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubScribeRepository subScribeRepository;

    @Transactional
    public void subscribe(Long fromUserId, Long toUserId) {
        subScribeRepository.mSubscribe(fromUserId, toUserId);
    }

    @Transactional
    public void unSubscribe(Long fromUserId, Long toUserId) {
        subScribeRepository.mUnSubscribe(fromUserId, toUserId);
    }

}
