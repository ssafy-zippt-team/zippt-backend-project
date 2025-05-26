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
                                "1. 아파트 상세 정보:\n" +
                                "%s\n" +
                                "2. 해당 아파트 반경 내 상권 정보:\n" +
                                "%s\n\n" +
                                "요청사항:\n" +
                                "- 출력은 HTML 코드만 (Markdown, ``` 코드블록 절대 금지)\n" +
                                "- 최상위 <div> 스타일: width:600px; height:500px; overflow-y:auto; font-family:Arial,sans-serif;\n" +
                                "- 섹션 구조:\n" +
                                "  1️⃣ 🌟 <strong>요약</strong>\n" +
                                "  2️⃣ 💰 <strong>최근 거래 내역</strong> (테이블)\n" +
                                "  3️⃣ 📝 <strong>최근 리뷰</strong> (리스트)\n" +
                                "  4️⃣ 🏆 <strong>평가</strong> (리스트)\n" +
                                "  5️⃣ 🏅 <strong>종합 점수</strong> (밑줄)\n" +
                                "- 각 섹션 제목은 <h3 style='border-bottom:2px solid #115C5E; margin-bottom:8px;'>…</h3> 로 작성\n" +
                                "- 중요 키워드는 <span style='background-color:yellow;'>…</span> 로 강조\n" +
                                "- 거래 내역은 <table style='width:100%%;border-collapse:collapse;'>…</table> 구조 사용\n" + // ← width:100%% 로 변경
                                "- 리뷰/평가는 <ul> 또는 <ol> 사용\n" +
                                "- 최종 종합 점수는 <span style='text-decoration:underline;'>…</span> 로 표시\n\n" +
                                "예시 전체 구조:\n" +
                                "<div style='width:600px;height:500px;overflow-y:auto;font-family:Arial,sans-serif;'>\n" +
                                "  <h3 style='border-bottom:2px solid #115C5E;margin-bottom:8px;'>🌟 <strong>[동이름] [아파트명] 요약 정보</strong></h3>\n" +
                                "  <p>…요약 내용…</p>\n\n" +
                                "  <h3 style='border-bottom:2px solid #115C5E;margin-bottom:8px;'>💰 최근 거래 내역</h3>\n" +
                                "  <table style='width:100%%;border-collapse:collapse;'>\n" +  // ← 여기도 마찬가지
                                "    <thead><tr><th>거래일</th><th>면적(㎡)</th><th>가격(만원)</th></tr></thead>\n" +
                                "    <tbody>\n" +
                                "      <tr><td>2025-02-26</td><td>101.88</td><td>38,500</td></tr>\n" +
                                "      <!-- … -->\n" +
                                "    </tbody>\n" +
                                "  </table>\n" +
                                "  <p>📈 평당 가격: 380</p>\n\n" +
                                "  <h3 style='border-bottom:2px solid #115C5E;margin-bottom:8px;'>📝 최근 리뷰</h3>\n" +
                                "  <ul><li>리뷰1: …</li><!-- … --></ul>\n\n" +
                                "  <h3 style='border-bottom:2px solid #115C5E;margin-bottom:8px;'>🏆 평가</h3>\n" +
                                "  <ol><li>입지: …</li><!-- … --></ol>\n\n" +
                                "  <h3 style='border-bottom:2px solid #115C5E;margin-bottom:8px;'>🏅 종합 점수: <span style='text-decoration:underline;'>85점</span></h3>\n" +
                                "</div>",
                        aptSeq, houseInfoRequestDto.toString(), commercialInfo))
                .tools(houseSummaryTool)
                .call()
                .content(); // 응답 결과에서 순수 문자열(HTML)을 추출
    }
}