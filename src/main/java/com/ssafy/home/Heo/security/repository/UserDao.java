package com.ssafy.home.Heo.security.repository;

import com.ssafy.home.Heo.security.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    boolean existsByUsername(String username);

    UserEntity findByUuid(String uuid);
    UserEntity findByUserId(int userid);
    UserEntity findByUserEmail(String userEmail);

    void registUser(UserEntity userEntity);

    String findPasswordByUuid(String uuid);
}
