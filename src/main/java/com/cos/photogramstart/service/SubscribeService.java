package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubScribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubScribeRepository subScribeRepository;
    private final EntityManager em;   // 모든  repostiory 는 entityManager로 구현해져있음

    @Transactional
    public void subscribe(Long fromUserId, Long toUserId) {
        try {
            subScribeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독을 하였습니다.");
        }
    }

    @Transactional
    public void unSubscribe(Long fromUserId, Long toUserId) {
        subScribeRepository.mUnSubscribe(fromUserId, toUserId);
    }

    @Transactional(readOnly = true)
    public List<SubscribeDto> subscribeList(Long principalId, Long pageUserId) {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select U.id, U.username,U.profileImageUrl, ");
        stringBuffer.append("if((select 1 from subscribe where fromUserId = ? AND toUserId = U.id), 1, 0) subscribeState, ");
        stringBuffer.append("if ((? = u.id), 1, 0) equalUserState ");
        stringBuffer.append("from user U inner join subscribe s ");
        stringBuffer.append("on u.id = s.toUserId where s.fromUserId = ?");
        stringBuffer.append("");
        stringBuffer.append("");

        // 쿼리 완성
        Query query = em.createNativeQuery(stringBuffer.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        // 쿼리 실행  (qlrm 라이브러리 사용)
        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtoList = result.list(query, SubscribeDto.class);

        return subscribeDtoList;
    }

}
