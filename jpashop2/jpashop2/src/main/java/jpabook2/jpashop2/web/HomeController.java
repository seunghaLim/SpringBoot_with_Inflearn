package jpabook2.jpashop2.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        log.info("home controller");
        return "home";
    }

}
