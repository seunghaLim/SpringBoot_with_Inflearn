package hello.hellosprint.service;

import hello.hellosprint.repository.MemberRepository;
import hello.hellosprint.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//회원 서비스와 회원 리포지토리의 @Service, @Repository, @Autowired 애노테이션을 제거하고 진행한다
@Configuration
public class SpringConfig {
    
    @Bean //가장 먼저 memberService를 빈으로 등록해주므로 service 패키지에 파일을 놔두나봄
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    
    @Bean //memberService를 빈에 올리고, memberService가 다시 DI로 MemberRepository를 받기 때문에 얘도 빈으로 올려줌
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
