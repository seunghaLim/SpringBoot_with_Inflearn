package hello.hellosprint.service;

import hello.hellosprint.domain.Member;
import hello.hellosprint.repository.MemberRepository;
import hello.hellosprint.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@Service // 어노테이션으로 얘가 서비스라고 알려줘야지 스프링이 스프링 빈으로 등록해줌
@Transactional // JPA에서 join 메소드 돌릴 때 필요한 건가봄
public class MemberService {

    private final MemberRepository memberRepository;

        //생성자 제작 외부에서 넣어준 memberRepository를 사용(DI)
        //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     회원가입
     */
    public Long join(Member member) {
        
        //동일한 이름의 회원 여러명은 가입 불가
        
        validateDuplicateMember(member); //extract method 단축키 컨트롤+알트+M

        memberRepository.save(member);
        return member.getId();
        
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다");
                        });
    }

    /**
     전체 회원 조회
     */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     아이디로 멤버 찾기
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
