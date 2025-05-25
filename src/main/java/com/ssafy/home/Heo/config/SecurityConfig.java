package com.ssafy.home.Heo.config;

import com.ssafy.home.Heo.security.jwt.JWTFilter;
import com.ssafy.home.Heo.security.jwt.JWTUtil;
import com.ssafy.home.Heo.security.jwt.LoginFilter;
import com.ssafy.home.Heo.security.service.RefreshTokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@Log4j2
// 메서드 레벨 @PreAuthorize 를 쓰려면 이 어노테이션을 추가
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil, RefreshTokenService refreshTokenService) {

        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
    }

    //AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }
    // password 암호화 위한 Bean 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 프론트와 백간의 연결에 있어 CORS 오류 해결(1)
        http.cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();
                        // 보통 프론트 서버는 3000번대로 이용
                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);

                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                        return configuration;
                    }
                })));

        http.csrf((auth) -> auth.disable());
        http.formLogin((auth -> auth.disable()));
        http.httpBasic((auth) -> auth.disable());

        // 경로별 인가 작업
        http.authorizeHttpRequests((auth) -> auth
                // 토큰 보유한 사용자 접근 가능
//                .requestMatchers("/api/v1/ai/**",
//                        "/api/v1/reviews/reviewInsert",
//                        "/api/v1/reviews/reviewUpdate",
//                        "/api/v1/reviews/{reviewId}"
//                        ).authenticated()
                // 모든 사용자 접근 가능
                .requestMatchers("/api/v1/cache/**").authenticated()                   
                .requestMatchers("/login",
                        "/",
                        "/api/v1/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**").permitAll()
                // 토큰 권한 관리자만 접근 가능
                .requestMatchers("/admin/*").hasRole("ADMIN")
                // 토큰 권한 유저만 접근 가능
                .requestMatchers("/user/*").hasRole("USER")
                .anyRequest().authenticated());

        http.addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

        // /login 요청 발생시 해당 요청을 가로채서 실행
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, refreshTokenService), UsernamePasswordAuthenticationFilter.class);

        // 세션 설정
        http.sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
