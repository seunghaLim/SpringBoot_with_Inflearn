package heiil.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 컴포넌트 스캔의 대상이 되면서 빈으로 등록됨
// @Component(memberService2)이렇게하면 내가 지정한 이름으로 빈 이름이 지정될 수 있음
public class MemberServiceImpl implements MemberService {

    // 인터페이스에만 의존함. 구현 객체에 의존하지 않음.
    private final MemberRepository memberRepository;

    @Autowired // 원래 방법으로는 AppConfig의 코드로 주입되었는데, 이젠 스프링 컨테이너가 자동으로 MemoryMemberRepository를 주입시켜줌
    // getBean("memberService", MemberService.class); 와 비슷한 코드라고 생각하면 됨
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {

        return memberRepository.findById(memberId);
    }

    //싱글톤 확인용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
