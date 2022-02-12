package jpabook2.jpashop2.service;

import jpabook2.jpashop2.domain.*;
import jpabook2.jpashop2.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @PersistenceContext
    EntityManager em;


    @Test
    public void 상품주문(){

        // given
        Member member = createMember();
        Item item = createBook("jpa", 10, 1000);

        // when
        Long orderId = orderService.order(member.getId(), item.getId(), 5);

        // then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        Assert.assertEquals("주문한 상품 종류 수가 정확해야 한다", 1, getOrder.getOrderItems().size());
        Assert.assertEquals("주문 가격은 가격 * 수량이다", 5*1000, getOrder.getTotalPrice());
        Assert.assertEquals("주문 수량만큼 재고가 줄어야 한다", 5, item.getStockQuantity());



    }


   @Test(expected = NotEnoughStockException.class)
    public void 재고수량오류(){

        Member member = createMember();
        Item item = createBook("jpa", 10, 1000);

        Long orderId = orderService.order(member.getId(), item.getId(), 15);

        org.junit.jupiter.api.Assertions.fail("재고수량 부족 오류가 발생해야 한다.");
    }

    @Test
    public void 주문취소(){

        // given
        Member member = createMember();
        Item item = createBook("jpa", 10, 1000);
        Long orderId = orderService.order(member.getId(), item.getId(), 1);

        // when
        orderService.cancelOrder(orderId);

        // then
        Order canceledOrder = orderRepository.findOne(orderId);

        Assertions.assertThat(canceledOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        Assertions.assertThat(item.getStockQuantity()).isEqualTo(10);


    }




    private Book createBook(String name, int quantity, int price ) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(quantity);
        book.setPrice(price);
        em.persist(book);

        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("kim");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
}
