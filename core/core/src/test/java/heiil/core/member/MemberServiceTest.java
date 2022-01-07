package heiil.core.member;

import heiil.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    // 기존코드
    // MemberService memberService = new MemberServiceImpl();

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){

        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }



    @Test
    void join(){
        //given memberA가 주어진 상황
        Member member = new Member(1L, "memberA", Grade.VIP );

        //when 얘가 가입했을때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then 가입한 애랑 찾은 애랑 같아야 함
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
