package jpabook.jpashop.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
public class Album extends Item{

    private String artist;
    private String etc;
}
