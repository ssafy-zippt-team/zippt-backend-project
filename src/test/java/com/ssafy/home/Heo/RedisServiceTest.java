package com.ssafy.home.Heo;


import com.ssafy.home.Heo.cache.dto.out.SearchWordDetailResponseDto;
import com.ssafy.home.Heo.cache.service.RedisService;
import lombok.extern.log4j.Log4j2;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class RedisServiceTest {
    @Autowired
    private RedisService redisService;


    @Test
    void addSearchWordTest(){
        redisService.addSearchWord("abcde", "강남");
    }

    @Test
    void getRecentSearchDtoTest(){
        List<SearchWordDetailResponseDto> list = redisService.getRecentSearchDto("abcde");
        list.forEach(dto -> log.info(dto.toString()));
    }

    @Test
    void deleteSearchWordTest(){
        redisService.deleteSearchWord("abcde","강남");
    }

    @Test
    void deleteUserSearchKeyTest(){
        redisService.deleteUserSearchKey("abcde");
    }


}
