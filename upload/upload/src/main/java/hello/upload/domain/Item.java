package hello.upload.domain;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private Long id;
    private String itemName;
    private UploadFile attachFile; // 첨부파일 이름 경로 (사용자버전 db버전)
    private List<UploadFile> imageFiles; // 이미지파일 여러개 경로
}
