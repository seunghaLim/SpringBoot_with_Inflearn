package hello.hellosprint.controller;

import hello.hellosprint.domain.Member;
import hello.hellosprint.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    
    private final MemberService memberService;

    @Autowired //어노테이션으로 여기에 자동으로 memberService를 연결해달라고 스프링한테 요청
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){ // createMemberForm.html에서 받은 name이 form에 저장
        Member member = new Member();
        member.setName(form.getName()); // form에서 이름을 가져와서 member 객체에 저장

        memberService.join(member); //member 객체를 회원가입시킴

        System.out.println("member =" + member.getName());

        return "redirect:/"; // 그 전화면으로 돌아감
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers(); // 리파지토리에 저장된 멤버 전부 찾음
        model.addAttribute("members", members); // 모델에 멤버 저장
        return "members/memberList";

    }





}
