package com.ssafy.home.Heo.house.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.home.Heo.common.base.BaseResponseStatus;
import com.ssafy.home.Heo.common.exception.BaseException;
import com.ssafy.home.Heo.config.VectorStoreConfig;
import com.ssafy.home.Heo.house.dto.out.HouseSimilarResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.json.Path;
import redis.clients.jedis.search.Document;
import redis.clients.jedis.search.Query;
import redis.clients.jedis.search.SearchResult;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class HouseVectorDaoImpl implements HouseVectorDao {
    private final VectorStore store;
    private final VectorStoreConfig vectorStoreConfig;



    @Override
    public List<HouseSimilarResponseDto> getHouseTermSimilar(String term) {
        SearchRequest.Builder builder = SearchRequest
                                                .builder().query(term)
                                                            .topK(4) // 4개 까지만
                                                            .similarityThreshold(0.9); // 1은 완전히 똑같음을 의미
        List<org.springframework.ai.document.Document> list = store.similaritySearch(builder.build());
        return list.stream()
                .map(doc -> HouseSimilarResponseDto.builder()
                        // Document의 text
                        .aptNm(doc.getText())
                        // metadata에서 원하는 필드 추출, null 안전하게 처리
                        .aptSeq((String) doc.getMetadata().get("apt_seq"))
                        .imgUrl((String) doc.getMetadata().get("img_url"))
                        .latitude(parseFloatSafe(doc.getMetadata().get("latitude")))
                        .longitude(parseFloatSafe(doc.getMetadata().get("longitude")))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<HouseSimilarResponseDto> getHouseImageSimilar(String aptSeq) throws Exception {
        // Redis 연결 설정
        String redisHost = vectorStoreConfig.getRedisHost();
        int redisPort = vectorStoreConfig.getRedisPort();
        String redisPassword = vectorStoreConfig.getRedisPassword();
        String index = "house-index";
        String prefix = "house:";
        String vectorKey = prefix + aptSeq + ":image";

        JedisPooled jedis = redisPassword.isBlank()
                ? new JedisPooled(redisHost, redisPort)
                : new JedisPooled(new HostAndPort(redisHost, redisPort),
                DefaultJedisClientConfig.builder()
                        .user("default")
                        .password(redisPassword)
                        .build());

        // Redis에서 embedding 벡터 가져오기
        Object vectorObj = jedis.jsonGet(vectorKey, Path.of("$.embedding"));
        if (vectorObj == null || vectorObj.toString().equals("[]")) {
            log.info("❌ embedding 벡터가 없습니다: " + vectorKey);
            throw new BaseException(BaseResponseStatus.NO_IMAGE_EXIST_HOUSE); // 이미지 없으면 에러 메시지
        }

        // ObjectMapper 인스턴스 생성 (JSON 직렬화/역직렬화용)
        ObjectMapper mapper = new ObjectMapper();
        // vectorObj를 JSON 문자열로 변환
        String vectorJson = mapper.writeValueAsString(vectorObj);
        // JSON 문자열을 다시 JsonNode(트리 구조 객체)로 파싱
        JsonNode node = mapper.readTree(vectorJson);
        // node가 배열이고, 길이가 1이면서 그 첫 번째 요소도 배열이면 → 내부 배열로 접근
        JsonNode arrNode = (node.isArray() && node.size() == 1 && node.get(0).isArray()) ? node.get(0) : node;

        float[] queryVector = new float[arrNode.size()];
        for (int i = 0; i < arrNode.size(); i++) {
            queryVector[i] = (float) arrNode.get(i).asDouble();
        }

        // float[] → byte[] (Redis에서 요구하는 FLOAT32 + LITTLE_ENDIAN)
        byte[] queryVecBytes = floatArrayToByteArray(queryVector);
        /*
            Redis 벡터 유사도 검색 쿼리 (KNN 4)
            - "KNN 4": 임베딩 벡터 기준으로 가장 가까운 5개(Neighbor)의 결과를 반환
            - "__embedding_score": 0이면 완전히 동일(=자기 자신), 값이 작을수록 더 유사
        */
        Query query = new Query("*=>[KNN 5 @embedding $BLOB]")
                .addParam("BLOB", queryVecBytes)
                .returnFields("apt_seq", "full_name", "img_url", "latitude", "longitude", "__embedding_score")
                .setSortBy("__embedding_score", true) // 오름차순: 유사도 높은 순서
                .limit(1, 4) // 자기자신은 빼고 유사한것 4개 가져옴
                .dialect(2); // RedisSearch의 쿼리 파서 "dialect 2" 사용 (최신 기능 및 KNN 지원 등)

        // 검색 실행
        SearchResult result = jedis.ftSearch(index, query);
        return result.getDocuments().stream()
                .map(doc -> HouseSimilarResponseDto.builder()
                        .aptSeq(doc.getString("apt_seq"))
                        .aptNm(doc.getString("full_name"))
                        .imgUrl(doc.getString("img_url"))
                        .latitude(parseFloatSafe(doc.get("latitude")))
                        .longitude(parseFloatSafe(doc.get("longitude")))
                        .build())
                .collect(Collectors.toList());
    }

    // float[] → byte[] 변환 함수
    private static byte[] floatArrayToByteArray(float[] arr) {
        ByteBuffer buffer = ByteBuffer.allocate(4 * arr.length).order(ByteOrder.LITTLE_ENDIAN);
        for (float v : arr) buffer.putFloat(v);
        return buffer.array();
    }

    private float parseFloatSafe(Object value) {
        if (value == null) return 0f;
        if (value instanceof Float) return (Float) value;
        if (value instanceof Double) return ((Double) value).floatValue();
        try {
            return Float.parseFloat(value.toString());
        } catch (NumberFormatException e) {
            return 0f;
        }
    }


}

