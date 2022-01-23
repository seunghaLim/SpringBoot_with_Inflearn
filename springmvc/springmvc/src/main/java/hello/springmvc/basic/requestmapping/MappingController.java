package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
public class MappingController {

    public Logger log = LoggerFactory.getLogger((getClass()));

    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    // 경로 변수
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){

        log.info("mappingPath userId={}", data);
        return "ok";

    }

    // 다중 경로 변수
    @GetMapping("/mapping/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable String orderId){

        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok2";

    }

    // 특정 파리미터 조건 매핑
    @GetMapping(value= "/mapping-param", params = "mode=debug")
    public String mappingParam(){
        log.info("mappingParam");
        return "ok";
    }

    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";

    }
    
    // html 요청 헤더 Content-Type과 매핑
    @PostMapping(value = "/mapping-consumes", consumes = "application/json")
    public String mappingConsume(){
        log.info("mappingConsume");
        return "okC";
    }
    
    // html 요청 헤더 Accept와 매핑
    @PostMapping(value = "/mapping-produces", produces = "text/html")
    public String mappingProduces(){
        log.info("mappingproduces");
        return "okP";
    }


}
