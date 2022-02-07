package helloJpa;

import javax.persistence.*;

@Entity // jpa가 관리할 객체
// @Table(name = "USER") // 어느 테이블에 삽입할 지 매핑
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_TABLE",
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
                    generator = "MEMBER_SEQ_GENERATOR")
    private Long id;
    private String name;

    public Member() {
    }


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
