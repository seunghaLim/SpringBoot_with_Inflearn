package hello.hellosprint.service;

import hello.hellosprint.domain.Member;
import hello.hellosprint.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// MemberService 파일에서 단축키 컨트롤+시프트+T로 해도 됨
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    //이전 동작 다시 실행 시프트 +f11
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    //테스트코드는 이름을 한글로 써도 됨
    void 회원가입() {

        //given 이러한 상황에서 - spring이라는 이름의 회원이 가입함
        Member member = new Member();
        member.setName("hello");

        //when 이걸 검증할 때 - 가입이 잘 이루어졌는지
        Long saveId = memberService.join(member);

        //then 이렇게 나와야 함 - 가입한 객체와 가입 전 객체의 아이디가 같은지 확인
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){

        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}