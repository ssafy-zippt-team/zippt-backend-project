package com.ssafy.home.Heo.security.service;

import com.ssafy.home.Heo.security.dto.out.CustomUserDetails;
import com.ssafy.home.Heo.security.entity.UserEntity;
import com.ssafy.home.Heo.security.repository.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    public CustomUserDetailsService(UserDao userDao) {

        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        UserEntity userData = userDao.findByUserEmail(userEmail);

        if(userData != null) {
            return new CustomUserDetails(userData);
        }

        return null;
    }

    public CustomUserDetails loadUserByUuid(String uuid) throws UsernameNotFoundException {

        UserEntity userData = userDao.findByUuid(uuid);

        if(userData != null) {
            return new CustomUserDetails(userData);
        }

        return null;
    }

    public UserDetails loadUserByUserEmail(String userEmail) throws UsernameNotFoundException {

        UserEntity userData = userDao.findByUserEmail(userEmail);

        if(userData != null) {
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
