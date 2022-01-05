package heiil.core.discount;

import heiil.core.member.Member;

public interface DiscountPolicy {

    //리턴값 할인 금액 (천원 할인되면 1000 리턴)
    int discount(Member member, int price);
}
