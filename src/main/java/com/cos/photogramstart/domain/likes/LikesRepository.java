package com.cos.photogramstart.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Modifying
    @Query(value = "insert into likes (imageId, userId, createDate) values (:imageId, :principleId, now())", nativeQuery = true)
    int mLikes(Long imageId, Long principleId);

    @Modifying
    @Query(value = "delete from likes where imageId = :imageId and userId = :principleId", nativeQuery = true)
    int mUnLikes(Long imageId, Long principleId);

}
