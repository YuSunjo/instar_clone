package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubScribeRepository extends JpaRepository<Subscribe, Long> {

    @Modifying   //insert, delete , update 를 네이티브쿼리로 만들려면 필요
    @Query(value = "INSERT into subscribe(fromUserId, toUserId, createDate) VALUES (:fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubscribe(Long fromUserId, Long toUserId);  // 1 (변경된 행의 개수가 리턴됨), -1 (insert 가 안됨)

    @Modifying
    @Query(value = "DELETE FROM subscribe where fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
    void mUnSubscribe(Long fromUserId, Long toUserId);

    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :principalId AND toUserId = :pageUserId", nativeQuery = true)
    int mSubscribeState(Long principalId, Long pageUserId);

    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId", nativeQuery = true)
    int mSubscribeCount(Long pageUserId);


    // 구독 여부 완성 쿼리    - 스칼라 서브 쿼리 select 안에 select 는 단일을 리턴턴
//   select u.id, u.username, u.profileImageUrl, (select true from subscribe where fromUserId = 1 AND toUserId = u.id ) subscribe
//    from user u inner join subscribe s on u.id = s.toUserId where s.fromUserId = 2

    // 동일 유저인지 판단하는 쿼리
//    select u.id, u.username, u.profileImageUrl,
//    if ((select 1 from subscribe where fromUserId = 1 AND toUserId = u.id ), 1, 0) subscribe
//            if((1 == u.id), 1, 0) equalUserState
//    from user u inner join subscribe s on u.id = s.toUserId where s.fromUserId = 2

}
