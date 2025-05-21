package com.ssafy.home.Heo.ai.advisor;

import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.client.advisor.api.AdvisedResponse;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisorChain;

import java.util.Map;

// TODO: 06-2. CallAroundAdvisor를 구현한 ReReadingAdvisor를 생성해보자.
//  https://docs.spring.io/spring-ai/reference/1.0/api/advisors.html#_re_reading_re2_advisor
public class ReReadingAdvisor implements CallAroundAdvisor {

    private int order;

    public ReReadingAdvisor(int order) {
        this.order = order;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    @Override
    public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {
        // 호출 전에 한번 더 읽어서 처리하기
        return chain.nextAroundCall(before(advisedRequest));
    }

    private AdvisedRequest before(AdvisedRequest request) {
        Map<String, Object> advisedUserParams = request.userParams();
        advisedUserParams.put("re2_input_query", request.userText());
        return AdvisedRequest.from(request).userText("""
                {re2_input_query}
                다시 한번 이 문장을 찬찬히 읽어봐.: {re2_input_query}
                """).userParams(advisedUserParams).build();
    }
}
