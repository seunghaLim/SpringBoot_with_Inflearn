package heiil.core;

import heiil.core.member.Grade;
import heiil.core.member.Member;
import heiil.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args){
        
        // 기존코드 - memberServiceImpl을 메인 메소드에서 내가 직접 생성해줌
        // MemberService memberService = new MemberServiceImpl();

        // 바뀐 코드 - memberServiceImpl를 appConfig가 대신 생성하고 주입시켜줌 근데 이 코드도 아직까지는 스프링 사용 안함
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        // 스프링 컨테이너가 이걸 대신 해주기 위해 필요한 코드
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //getBean순서 - 메소드명, 타입
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member: " + member.getName());
        System.out.println("find member: " + findMember.getName());

    }
}
