package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {

    // 바디에 String 반환

    // response 객체 사용할 경우 - IOException throws 해야함
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException{

        response.getWriter().write("ok");
    }

    // ResponseEntity 사용하는 경우 - 상태코드와 함께 반환 가능
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {

        return "ok";
    }



    //바디에 json 타입 데이터 반환

    // ResponseEntity 사용해서 내가 만든 객체 반환. 상태코드와 함께 반환해야함
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {

        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(23);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }
    
    // @ResponseBody 로 ResponseEntity 없이 그냥 반환할 수 있음. 
    @ResponseStatus(HttpStatus.OK) // 이러면 상태코드 반환을 못하므로 어노테이션 @ResponseStatus을 통해 상태코드 지정
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2(){

        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(23);

        return helloData;
    }


}
