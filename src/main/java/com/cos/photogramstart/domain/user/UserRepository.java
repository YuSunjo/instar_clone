package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // jpa method names  query creation
    User findByUsername(String username);

}
