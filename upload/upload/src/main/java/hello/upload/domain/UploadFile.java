package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName; // 사용자가 업로드한 파일 이름
    private String storeFileName; // 데이터베이스에 저장할 때 사용하는 파일 이름(UUID로 개발자가 지정)

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
