package hello.typeconverter.helloController;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class FormatterController {

    @GetMapping("/formatter/edit")
    public String formatterForm(Model model){

        Form form = new Form();
        form.setNumber(10000); // 출력 시 포맷팅 적용되어서 10,000으로 출력
        form.setLocalDateTime(LocalDateTime.now()); // 2022-02-02 01:33:19으로 출력
        model.addAttribute("form", form);
        return "formatter-form";

    }

    // "10,000" -> 10000
    // "2022-02-02 01:24:30" -> 2022-02-02T01:24:30
    @PostMapping("/formatter/edit")
    public String formatterEdit (@ModelAttribute Form form){
        return "formatter-view";
    }


    @Data
    static class Form{

        @NumberFormat(pattern = "###,###")
        private Integer number;

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime localDateTime;
    }
}
