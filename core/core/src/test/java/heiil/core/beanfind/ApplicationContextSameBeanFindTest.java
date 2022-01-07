package heiil.core.beanfind;

import heiil.core.member.MemberRepository;
import heiil.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {

    // 파라미터로 SameConfig 넣어줌
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameConfig.class);

    // 임의의 SameConfig 제작 - 똑같은 타입 두 개가 스프링 빈으로 등록되도록
    // 클래스 안에 static으로 선언하면 스코프가 그 안에서만(?) 생김

    @Configuration // 이거 있으면 스프링 컨테이너가 예를 설정으로 인식
    static class SameConfig{

        @Bean
        MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }

    }

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면, 중복 오류 발생")
    void findBeanByTypeDuplicate(){

        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 오류 없음")
    void findBeanByName(){

        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }
    
    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeansByType(){
        // ac.getBeansOfType() 해당 타입의 빈을 모두 가져옴 반환값 Map
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key= " + key + "beansOfType = " +  beansOfType.get(key));
        }
        System.out.println("beansOfType= " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }


}
