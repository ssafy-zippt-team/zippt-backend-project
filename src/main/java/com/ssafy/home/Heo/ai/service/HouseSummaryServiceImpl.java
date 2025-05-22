package com.ssafy.home.Heo.ai.service;

import com.ssafy.home.Heo.ai.dto.in.HouseInfoRequestDto;
import com.ssafy.home.Heo.ai.tools.HouseSummaryTool;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
//@RequiredArgsConstructor
public class HouseSummaryServiceImpl implements HouseSummaryService {

//    @Qualifier("reReadingChatClient")
    private final ChatClient houseSummaryClient; // AI 호출용 클라이언트 (tool 호출 가능)
//
    private final HouseSummaryTool houseSummaryTool; // 아파트 요약용 툴
    public HouseSummaryServiceImpl(@Qualifier("reReadingChatClient") ChatClient houseSummaryClient,
                                   HouseSummaryTool houseSummaryTool) {
        this.houseSummaryClient = houseSummaryClient;
        this.houseSummaryTool = houseSummaryTool;
    }

    @Override
    public String generateHouseSummaryHtml(String aptSeq, HouseInfoRequestDto houseInfoRequestDto,
                                           String commercialInfo) {

        return houseSummaryClient.prompt()
                .system(c -> c.param("language", "Korean").param("character", "전문가"))
                .user(String.format(
                        "아파트 코드 %s에 대한 정보를 요약하고 객관적으로 평가해줘.\n" +
                                "다음은 해당 아파트의 상세 정보야: %s\n" +
                                "해당 아파트 반경 내 상권 정보는 다음과 같아: %s\n" +
                                "반환 결과는 마크다운이나 코드블록 없이 순수 HTML 문자열로만 줘. 개행도 br태그를 사용해.\n" +
                                "설명은 <p> 태그로 해줘. HTML은 style 태그를 인라인으로 넣고, 가로 600px, 세로 500px, 스크롤 가능한 구조로 디자인해줘.\n" +
                                "가능하면 시각자료도 첨부해줘.",
                        aptSeq, houseInfoRequestDto.toString(), commercialInfo))
                .tools(houseSummaryTool)
                .call()
                .content(); // 응답 결과에서 순수 문자열(HTML)을 추출
    }
}
