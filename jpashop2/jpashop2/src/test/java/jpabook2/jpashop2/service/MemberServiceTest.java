package jpabook2.jpashop2.service;

import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception {

        // given
        Member member = new Member();
        member.setName("memberA");

        // when
        Long findMemberId = memberService.join(member);

        // then
        Assertions.assertEquals(member, memberRepository.findOne(findMemberId));
    }

    @Test(expected = IllegalStateException.class) // try catch 문 대신에 이거 써줘도 됨
    public void 중복_회원_예외() throws Exception{
        
        // given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
        
        // when
        memberService.join(member1);
        memberService.join(member2); // 여기서 터진 예외가 IllegalStateException.class라면 테스트 성공
        
        // then
        // 코드가 여기까지 오면 안되는 상황(그 전에 예외가 터져서 못 오는 상황이어야 함)에서 왔으면 에러 발생
        Assertions.fail("예외가 발생해야 한다");

//        어노테이션으로 아래 코드 생략 가능
//        memberService.join(member1);
//        try{
//            memberService.join(member2);
//        }catch (IllegalStateException e){
//            return;
//        }
//        Assertions.fail("예외가 발생해야 한다");



    }


}
