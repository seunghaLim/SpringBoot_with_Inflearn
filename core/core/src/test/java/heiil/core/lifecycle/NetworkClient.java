package heiil.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
 
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect: " + url);
    }

    public void call(String message){
        System.out.println("call: " + url + " message: " + message);
    }

    //서비스 종료 시 호출
    public void disconnect(){
        System.out.println("close: " + url);
    }

    @PostConstruct //초기화 메소드 위에 붙여줌
    public void init() {
        // 커넥트와 콜과 같은 초기화 작업을 이 메소드 안에서 수행
        System.out.println("초기화 콜백 메소드 실행");
        connect();
        call("초기화 연결 메시지");

    }

    @PreDestroy // 종료 콜백 위에 붙여줌
    public void close() {
        // 소멸전 콜백을 여기서 수행
        System.out.println("소멸전 콜백 메소드 실행");
        disconnect();
    }
}
