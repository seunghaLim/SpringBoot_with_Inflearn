package hello.hellosprint.repository;
import hello.hellosprint.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();


   //void clearStore(); 이거 없어야함
}
