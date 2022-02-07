package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    private String city;
    private String zipcode;
    private String street;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    // 연관관계 편의 메서드
    public void changeOrders(Order order) {
        orders.add(order);
        order.setMember(this);
    }
}
