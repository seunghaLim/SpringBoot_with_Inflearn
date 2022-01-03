package hello.hellosprint.domain;

import javax.persistence.*;

//회원 도메인 제작
@Entity // JPA가 관리하는 entity가 됨
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
