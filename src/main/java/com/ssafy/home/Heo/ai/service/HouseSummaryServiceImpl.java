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
                        "다음은 특정 아파트에 대한 요약 및 평가 요청임.\n" +
                                "1. 아파트 상세 정보:\n%s\n" +
                                "2. 해당 아파트 반경 내 상권 정보:\n%s\n\n" +
                                "요청사항:\n" +
                                "- HTML 코드만 반환. 마크다운(Markdown)이나 ``` 코드블록 절대 사용 금지.\n" +
                                "- 전체 HTML은 <html> 태그 없이 <div>, <p> 등 태그로만 구성.\n" +
                                "- 설명은 모두 <p> 태그로 감쌀 것.\n" +
                                "- style 속성은 인라인 방식으로 작성. 따로 <style>태그 작성하지 말고 html코드 내부에 인라인 삽입\n" +
                                "- 최상위 div의 style은: width: 600px; height: 500px; overflow-y: scroll;\n" +
                                "- 내용 길 경우 스크롤 가능해야 함.\n" +
                                "- 첫 문장은 반드시 \"[동이름(umdNm)] [아파트이름(aptNm)] 요약 정보.\" 형식으로 시작하고 볼드처리 (예: 명륜1가 리치캐슬아파트 요약 및 평가 정보입니다.). aptSeq는 포함하지 말 것.\n" +
                                "- 특히 최근 거래 내역은 요약,평 별 거래가격, 평 당 가격, 추세 등 최소 5~6로 잘 표현." +
                                "- 마지막은 100점 만점 기준 평가 점수 포함 (예: 종합 점수: 85점 , 점수는 밑줄처리). 점수 기준 포함\n" +
                                "- 핵심 정보는 강조 (예: <span style='background-color: yellow;'>중요</span>).\n"+
                                "- 정리하면 각 단계는 첫 문장->최근 거래 내역->최근 리뷰 항목->평가-> 최종점수 로 구성. \n"+
                                "- 가로와 세로 크기를 고려해 풍부한 내용을 작성(비어보이면 안됨).요약 보다 평가에 더 신경써줘 최소 3~4줄\n"+
                                "- 각 단계마다 꼭 볼드처리와 개행(br태그) 2개씩 적용, 단계마다 문단은 설명 텍스트보다 크게 작성. 고급스러운 디자인으로 제공해.",

                        aptSeq, houseInfoRequestDto.toString(), commercialInfo))
                .tools(houseSummaryTool)
                .call()
                .content(); // 응답 결과에서 순수 문자열(HTML)을 추출
    }
}