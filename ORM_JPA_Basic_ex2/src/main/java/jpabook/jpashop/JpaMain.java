package jpabook.jpashop;

import jpabook.jpashop.domain.*;

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


            Book book = new Book();
            book.setName("jpa");
            book.setAuthor("kim");

            em.persist(book);

//            JPQL - select m form Member m where m.age > 18
//            JPAFactoryQuery query = new JPAQueryFactory(em);
//            QMember m = QMember.member;
//            List<Member> list =
//                    query.selectFrom(m)
//                            .where(m.age.gt(18))
//                            .orderBy(m.name.desc())
//                            .fetch();

//            네이티브 SQL
//            String sql = “SELECT ID, AGE, TEAM_ID, NAME FROM MEMBER WHERE NAME = ‘kim’";
//            List<Member> resultList =
//                    em.createNativeQuery(sql, Member.class).getResultList();


            // 트랜젝션 커밋해서 변경사항 반영
            tx.commit();

        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }

}
