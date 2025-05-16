package com.ssafy.home.Heo.vector;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

// 생략된 import 및 @Component 선언 동일
@Configuration
public class HouseEmbeddingRunner {

    private final JdbcTemplate jdbcTemplate;
    private final EmbeddingModel embeddingModel;
    private final VectorStore vectorStore;

    private static final String S3_BASE_URL = "https://ssafyhomebusan.s3.ap-southeast-2.amazonaws.com";

    public HouseEmbeddingRunner(JdbcTemplate jdbcTemplate,
                                EmbeddingModel embeddingModel,
                                VectorStore vectorStore) {
        this.jdbcTemplate = jdbcTemplate;
        this.embeddingModel = embeddingModel;
        this.vectorStore = vectorStore;
    }

    public void run() {
        String sql = "SELECT apt_seq, umd_nm, apt_nm, img_url FROM houseinfos";
        List<HouseInfo> houses = jdbcTemplate.query(sql, (rs, rowNum) -> new HouseInfo(
                rs.getString("apt_seq"),
                rs.getString("umd_nm"),
                rs.getString("apt_nm"),
                rs.getString("img_url")
        ));

        System.out.println("🚀 총 " + houses.size() + "건 임베딩 시작...");

        int total = houses.size();
        for (int i = 0; i < total; i++) {
            HouseInfo house = houses.get(i);
            int remaining = total - i - 1;
            String fullName = house.umdNm() + " " + house.aptNm();

            if (house.imgUrl() != null && (house.imgUrl().endsWith(".jpg") || house.imgUrl().endsWith(".png"))) {
                String imageUrl = S3_BASE_URL + house.imgUrl();
                String content = "이미지 URL: " + imageUrl;
                Document doc = new Document(content, Map.of(
                        "apt_seq", house.aptSeq(),
                        "full_name", fullName,
                        "img_url", imageUrl
                ));
                vectorStore.add(List.of(doc));
//                System.out.println("🖼 이미지 저장 완료: " + house.aptSeq() + " | 남은 건수: " + remaining);
                continue;
            }

            if (house.imgUrl() != null && house.imgUrl().endsWith(".jpg")) {
                try {
                    URL pdfUrl = new URL(S3_BASE_URL + house.imgUrl());
                    InputStream inputStream = pdfUrl.openStream();
                    InputStreamResource resource = new InputStreamResource(inputStream);

                    PagePdfDocumentReader reader = new PagePdfDocumentReader(resource,
                            PdfDocumentReaderConfig.builder()
                                    .withPageTopMargin(0)
                                    .withPagesPerDocument(1)
                                    .build());

                    List<Document> pdfDocs = reader.read();
                    pdfDocs.forEach(doc -> doc.getMetadata().putAll(Map.of(
                            "apt_seq", house.aptSeq(),
                            "full_name", fullName,
                            "img_url", S3_BASE_URL + house.imgUrl()
                    )));
                    vectorStore.add(pdfDocs);
//                    System.out.println("📄 jpg 저장 완료: " + house.aptSeq() + " | 남은 건수: " + remaining);
                    continue;
                } catch (Exception e) {
                    System.out.println("⚠️ jpg 읽기 실패: " + house.imgUrl() + " → " + e.getMessage());
                }
            }

            String content = fullName;
            Document doc = new Document(content, Map.of(
                    "apt_seq", house.aptSeq(),
                    "full_name", fullName,
                    "img_url", house.imgUrl() == null ? "" : S3_BASE_URL + house.imgUrl()
            ));
            vectorStore.add(List.of(doc));
            System.out.println("✅ 텍스트 저장 완료: " + house.aptSeq() + " | 남은 건수: " + remaining);
        }

        System.out.println("🎉 모든 임베딩 완료!");
    }

    public record HouseInfo(String aptSeq, String umdNm, String aptNm, String imgUrl) {}
}

