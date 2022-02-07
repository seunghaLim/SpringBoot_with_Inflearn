package hello.upload.domain;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private Long id;
    private String itemName;
    private UploadFile attachFile; // 첨부파일용 UploadFile 객체 (UploadFile이란 객체로 선언해주었는데 여기엔 사용자버전 이름과 db버전 이름이 있음)
    private List<UploadFile> imageFiles; // 이미지파일용 UploadFile 객체
}
