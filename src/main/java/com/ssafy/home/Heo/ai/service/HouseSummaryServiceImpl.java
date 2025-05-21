package com.ssafy.home.Heo.ai.service;

import com.ssafy.home.Heo.ai.tools.HouseSummaryTool;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseSummaryServiceImpl implements HouseSummaryService{
    private final ChatClient houseSummaryClient; // AI 호출용 클라이언트 (tool 호출 가능)
    private final HouseSummaryTool houseSummaryTool; // 아파트 요약용 툴


    @Override
    public String generateHouseSummaryHtml(String aptSeq) {
        return houseSummaryClient.prompt()
                .tools(houseSummaryTool)
                .system("당신은 부동산 전문가입니다. 사용자에게 아파트 정보를 요약해 HTML 형식으로 알려주세요.")
                .user(String.format("아파트 코드 %s에 대한 정보를 요약하고 평가해줘. 결과는 HTML 형식으로 구성해줘.", aptSeq))
                .call()
                .content(); // 응답 결과에서 순수 문자열(HTML)을 추출
    }
}
