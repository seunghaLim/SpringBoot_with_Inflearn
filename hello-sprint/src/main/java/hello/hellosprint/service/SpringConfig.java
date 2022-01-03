package hello.hellosprint.service;

import hello.hellosprint.repository.JdbcTemplateMemberRepository;
import hello.hellosprint.repository.JpaMemberRepository;
import hello.hellosprint.repository.MemberRepository;
import hello.hellosprint.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//회원 서비스와 회원 리포지토리의 @Service, @Repository, @Autowired 애노테이션을 제거하고 진행한다
@Configuration
public class SpringConfig {

    //private DataSource dataSource;

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

//    @Autowired // jdbc 템플릿 쓸 때 필요
//    public SpringConfig() {
//        this.dataSource = dataSource;
//    }

    @Bean //가장 먼저 memberService를 빈으로 등록해주므로 service 패키지에 파일을 놔두나봄
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    
    @Bean //memberService를 빈에 올리고, memberService가 다시 DI로 MemberRepository를 받기 때문에 얘도 빈으로 올려줌
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository(); 메모리 쓸 때 리턴값
        //return new JdbcTemplateMemberRepository(dataSource); // jdbc 템플릿 쓸 때 필요
        return new JpaMemberRepository(em); // jpa쓸 때 필요함
    }
}
