package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    /**
     * 반환 타입이 없으면서(void) 이렇게 응답에 값을 직접 집어넣으면, view 조회X
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse
            response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    // 제일 축약한 버전. 변수명과 @RequestParam 모두 생략
    @ResponseBody // @RestController를 넣는 것과 같은 효과. 반환값 String과 같은 뷰 객체를 찾는게 아니라 html 메시지 바디에 그대로 내려준다
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            String username,
            int age){

        log.info("username = {}, age= {}", username, age );
        return "ok";
    }

    // 쿼리 파라미터에username은 무조건 들어오고 age는 안들어와도 됨 다면 age는 int말고 Integer로 선언해야 한다
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age){

        log.info("username = {}", username);
        return "ok";
    }

    // defaultValue = "기본값". 파라미터 안들어오면 이 기본값으로 값이 설정됨
    @ResponseBody
    @RequestMapping("/request-param-defaultValue")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age){

        log.info("username = {}, age= {}", username, age );
        return "ok";
    }

    // 맵으로 모든 요청 파라미터 전부 받기
    @ResponseBody
    @RequestMapping("/request-param-Map")
    public String requestParamDefault(
            @RequestParam Map<String, Object> paramMap){

        log.info("username = {}, age= {}", paramMap.get("username"), paramMap.get("age") );
        return "ok";
    }

    // 가장 기본적인 방식
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }


    // @ModelAttribute 생략 가능
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2( HelloData helloData){

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

}
