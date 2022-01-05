package heiil.core.discount;

import heiil.core.member.Grade;
import heiil.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//컨트롤+시프트+T 누르면 자동으로 만들어짐
class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 되어야 한다")
    void vip_o(){
        //given vip인 멤버가 주어진 상황에서
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when RateDiscount 정책을 사용해 만원짜리 물건을 구입했을 시
        int discount = discountPolicy.discount(member, 10000);

        //then 천원이 나와야 한다
        Assertions.assertThat(discount).isEqualTo(1000);

    }

    //실패 케이스 테스트
    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void vip_x(){
        //given vip가 아닌 멤버가 주어진 상황에서
        Member member = new Member(2L, "memberB", Grade.BASIC);

        //when RateDiscount 정책을 사용해 만원짜리 물건을 구입했을 시
        int discount = discountPolicy.discount(member, 10000);

        //then 0원이 나와야 한다
        Assertions.assertThat(discount).isEqualTo(0);

    }

}