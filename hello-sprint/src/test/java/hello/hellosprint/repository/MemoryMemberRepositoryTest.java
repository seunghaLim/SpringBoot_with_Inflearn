package hello.hellosprint.repository;

import hello.hellosprint.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*; // 알트+엔터 누르고 이거 추가하면 아래 assertThat에 길게 써줄 필요 없음

public class MemoryMemberRepositoryTest {

    // 부모 클래스 MemoryMemberRepository임 MemberRepository가 아니라.......
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트벤치할 때 테스트가 끝날때마다 메모리를 지워주는 메소드 필요
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // Optional로 감싸주었기 때문에 get메소드로 꺼내줘야 함
        //Assertions.assertEquals(member, result);
        assertThat(result).isEqualTo(member);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); //시프트+f6키로 이름 한번에 바꿀 수 있음
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1); // 멤버1이랑 가져온 값이랑 같은지 확인

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result  = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }



}
