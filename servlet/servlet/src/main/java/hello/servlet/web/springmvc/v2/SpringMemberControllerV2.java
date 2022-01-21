package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    @RequestMapping("/new-form")
    public ModelAndView newForm(){
        return new ModelAndView("new-form");
    }

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/members/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {

        // request에서 파라미터값을 직접 얻는 대신 프론트 컨트롤러에서 넘겨받은 paramMap에서 파라미터 값을 찾아 사용 -> 서블릿에서 독립
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);

        memberRepository.save(member);

        // 모델 뷰에 논리 이름과 데이터 넣어서 반환 (이 다음에 어느 jsp로 갈지 아는 애는 개별 컨트롤러임 그래서 여기에 논리 이름을 넣어주는거)
        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }

    @RequestMapping
    public ModelAndView members() {

        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);

        return mv;

    }
}
