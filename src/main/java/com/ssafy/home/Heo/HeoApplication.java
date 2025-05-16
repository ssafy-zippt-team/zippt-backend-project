package com.ssafy.home.Heo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.ssafy.home.Heo.vector.HouseEmbeddingRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HeoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HeoApplication.class, args);

		// HouseEmbeddingRunner 실행
		HouseEmbeddingRunner runner = context.getBean(HouseEmbeddingRunner.class);
		runner.run(); // 임베딩 수행
	}
}