package jpabook.jpashop;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // EntityManager 가져옴 - 고객 요청이 올 때 db작업을 해야 되는 경우라면 em을 통해서 해야만 함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        // 트랜젝션 가동
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{ // 필요한 로직 수행


            Order order = new Order();
            order.addOrderItem(new OrderItem());


            // 트랜젝션 커밋해서 변경사항 반영
            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }

}
