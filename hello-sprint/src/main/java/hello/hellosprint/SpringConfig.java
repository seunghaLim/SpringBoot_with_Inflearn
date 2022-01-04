package hello.hellosprint;
import hello.hellosprint.repository.*;
import hello.hellosprint.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    //스프링 데이터 jpa로 돌릴 때
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }


//    private DataSource dataSource;
//
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
//
//    @Autowired // jdbc 템플릿 쓸 때 필요
//    public SpringConfig() {
//        this.dataSource = dataSource;
//    }
//
//    @Bean //memberService랑
//    public MemberService memberService(){
//        return new MemberService(memberRepository());
//    }
//
//    @Bean //memberService를 빈에 올리고, memberService가 다시 DI로 MemberRepository를 받기 때문에 얘도 빈으로 올려줌
//    public MemberRepository memberRepository(){
//        //return new MemoryMemberRepository(); 메모리 쓸 때 리턴값
//        //return new JdbcTemplateMemberRepository(dataSource); // jdbc 템플릿 쓸 때 필요
//        return new JpaMemberRepository(em); // jpa쓸 때 필요함
//    }


}