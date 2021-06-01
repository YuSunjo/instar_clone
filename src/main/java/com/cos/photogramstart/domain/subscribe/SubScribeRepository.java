package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubScribeRepository extends JpaRepository<Subscribe, Long> {

    @Modifying   //insert, delete , update 를 네이티브쿼리로 만들려면 필요
    @Query(value = "INSERT into subscribe(from_user_id, to_user_id, createdDate) VALUES (:fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubscribe(Long fromUserId, Long toUserId);  // 1 (변경된 행의 개수가 리턴됨), -1 (insert 가 안됨)

    @Modifying
    @Query(value = "DELETE FROM subscribe where from_user_id = :fromUserId AND to_user_id = :toUserId", nativeQuery = true)
    void mUnSubscribe(Long fromUserId, Long toUserId);


}
