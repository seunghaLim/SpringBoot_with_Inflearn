package heiil.core;

import heiil.core.member.MemberRepository;
import heiil.core.member.MemberService;
import heiil.core.member.MemberServiceImpl;
import heiil.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(

        // 기존 AppConfig나 TestConfig 예제를 지웠어야 했는데 안지워줘서 필터 기능으로 그 예제들은 스캔안하도록 배제해줌
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =
                Configuration.class)
        // basePackageClasses = AutoAppConfig.class,
)
public class AutoAppConfig {
    
//        AppConfig처럼 주입 코드를 일일이 안적어줘도 됨 (아래처럼)
//        public MemberService memberService (){
//            return new MemberServiceImpl(memberRepository());
//        }

    
//    똑같은 이름으로 수동 방식으로 빈과 자동 방식 빈으로 등록할 때 수동 방식의 빈 자동 빈을 오버라이딩함 (오류없음)
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }

}
