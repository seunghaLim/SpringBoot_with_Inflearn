package heiil.core.member;

public class MemberServiceImpl implements MemberService {

    // 인터페이스에만 의존함. 구현 객체에 의존하지 않음.
    private final MemberRepository memberRepository;

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
