package jpabook2.jpashop2.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
@Table(name = "order_item") // 지정 안하면 클래스 이름이 테이블 이름으로 되나봄
@NoArgsConstructor(access = AccessLevel.PROTECTED) // new OrderItem() 못함
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice;
    private int count;


    // 생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }


    // 비즈니스 로직
    // 주문 취소
    public void cancel() {
        getItem().addStock(count);

    }

    
    // 조회 로직
    // 주문 총 가격
    public int getTotalPrice() {
        return getOrderPrice() * getCount();

    }
}
