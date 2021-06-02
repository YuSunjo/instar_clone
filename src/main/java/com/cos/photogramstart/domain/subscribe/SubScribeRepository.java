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


}
