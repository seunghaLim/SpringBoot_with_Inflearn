package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {


    // ModelAndView를 사용한 버전 - 객체에 모델(데이터)와 뷰(뷰 논리 이름)이 같이 담김
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello");
        mav.addObject("data", "hello");
        return mav;

    }

    // @ResponseBody를 쓰거나 @RestController를 쓴 경우 response/hello 이게 http 메시지 바디 부분에 찍힘
    // Model을 사용한 버전 - model 객체에는 데이터만 담고 반환된 문자열을 뷰 논리이름으로 인식
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){

        model.addAttribute("data", "hello");
        return "response/hello";

    }

    // 경로 이름과 리턴값 문자열이 같다면 리턴값 없어도 됨
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){

        model.addAttribute("data", "hello");

    }

}
