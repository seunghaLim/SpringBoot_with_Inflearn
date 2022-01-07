package heiil.core.order;

import heiil.core.AppConfig;
import heiil.core.member.Grade;
import heiil.core.member.Member;
import heiil.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

//    이전코드
//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();


    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }


    @Test
    void createOrder(){
        // 가상의 멤버 하나 가입시킴
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 주문 임의로 넣고 주문값이 잘 나왔는지 확인
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

}
