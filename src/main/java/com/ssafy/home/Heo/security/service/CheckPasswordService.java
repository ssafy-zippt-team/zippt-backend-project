package com.ssafy.home.Heo.security.service;

import com.ssafy.home.Heo.security.repository.UserDao;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CheckPasswordService {
    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CheckPasswordService(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean chekPassword(String uuid, String password) {

        // 1) DB에서 해시된 비밀번호를 조회
        String storedHash = userDao.findPasswordByUuid(uuid);
        System.out.println("store : " + storedHash);
        System.out.println("password : " + password);

        // 2) 평문(dto.getPassword()) 과 해시된 비밀번호를 비교
        return bCryptPasswordEncoder.matches(password, storedHash);
    }
}
