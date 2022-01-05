package heiil.core.order;

import heiil.core.discount.DiscountPolicy;
import heiil.core.discount.FixDiscountPolicy;
import heiil.core.discount.RateDiscountPolicy;
import heiil.core.member.MemberRepository;
import heiil.core.member.MemberService;
import heiil.core.member.MemberServiceImpl;
import heiil.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 애플리케이션에 대한 환경 구성은 전부 여기서 수행. 즉, 객체를 여기서 만들어서 주입함
@Configuration // 스프링이 대신 해주기 위해 필요한 어노테이션
public class AppConfig {

    //생성자를 통해 객체를 주입시킴 -> 생성자 주입을 통해 제작
    @Bean
    public MemberService memberService (){
        //MemberServiceImpl 생성자를 통해 MemoryMemberRepository 객체 주입
        return new MemberServiceImpl(memberRepository());
    }

    // 역할 - 구현이 뭔지 한눈에 볼 수 있음
    @Bean // 어노테이션을 통해 빈으로 등록. 보통 등록될 때 메소드 이름으로 등록됨
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //할인 정책을 바꾸고 싶으면 여기만 교체하면 됨
       return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
    }
}
