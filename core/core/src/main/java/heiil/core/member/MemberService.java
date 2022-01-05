package heiil.core.member;

public interface MemberService {

    //public 선언 안함함
   void join(Member member);

    Member findMember(Long memberId);

}
