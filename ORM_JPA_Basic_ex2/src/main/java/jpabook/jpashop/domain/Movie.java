package jpabook.jpashop.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
public class Movie extends Item{

    private String director;
    private String actor;
}
