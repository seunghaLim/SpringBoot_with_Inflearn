package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        // request에서 파라미터값을 직접 얻는 대신 프론트 컨트롤러에서 넘겨받은 paramMap에서 파라미터 값을 찾아 사용 -> 서블릿에서 독립
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);

        memberRepository.save(member);
        
        // 모델 뷰에 논리 이름과 데이터 넣어서 반환 (이 다음에 어느 jsp로 갈지 아는 애는 개별 컨트롤러임 그래서 여기에 논리 이름을 넣어주는거)
        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv;
    }
}
