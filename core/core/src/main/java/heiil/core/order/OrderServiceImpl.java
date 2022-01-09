package heiil.core.order;

import heiil.core.order.annotation.MainDiscountPolicy;
import heiil.core.discount.DiscountPolicy;
import heiil.core.member.Member;
import heiil.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 자동으로 만들어줌. 생성자 따로 만들 필요 없음
public class OrderServiceImpl implements OrderService{
    
    // 인터페이스에만 의존함. 구현 객체에 의존하지 않음.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    롬복 라이브러리의 @RequiredArgsConstructor 사용 시 생성자 전부 생략해줄 수 있음
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    // 아이디, 상품명, 상품가격을 넘겨받음
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        
        //할인가를 알 수 있는 discountPolicy - 아규먼트로 member를 받기 때문에 아이디로 member 찾음
        Member member = memberRepository.findById(memberId);
        //discountPrice로 할인가 찾음
        int discountPrice = discountPolicy.discount(member, itemPrice);
        
        //주문엔터티 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


    //싱글톤 확인용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
