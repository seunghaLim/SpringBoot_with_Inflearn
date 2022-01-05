package heiil.core.order;

public interface OrderService {
    //회원 아이디, 상품명, 상품가격 주면 할인 가격 리턴해줌
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
