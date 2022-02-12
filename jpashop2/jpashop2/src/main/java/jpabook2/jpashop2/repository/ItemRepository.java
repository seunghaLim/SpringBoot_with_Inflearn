package jpabook2.jpashop2.repository;

import jpabook2.jpashop2.domain.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Item item){
        if (item.getId() == null){
            em.persist(item); // 새로운 객체를 저장
        }
        else {
            em.merge(item); // 이미 저장되어 있던 객체를 업데이트
        }
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }


}
