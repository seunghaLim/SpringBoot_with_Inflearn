package heiil.core.beanfind;

import heiil.core.member.MemberService;
import heiil.core.member.MemberServiceImpl;
import heiil.core.order.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContestBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        // memberService가 MemberServiceImpl.class의 인스턴스인가?
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        //매개변수에 타입만 넣어줄 수 있음
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 인터페이스가 아니라 구현 객체로 조회
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX() {
        // 없는 빈 이름으로 조회하면 오류남
        // org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'XXXX' available
        // MemberService xxxx = ac.getBean("XXXX", MemberService.class);

        // 예외가 던져지면 성공한 것
        // assertThrows - 두번째 매개변수를 실행했을 시 첫번째 매개변수로 넣은 오류 터저야 함
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("XXXX", MemberService.class));
    }



}