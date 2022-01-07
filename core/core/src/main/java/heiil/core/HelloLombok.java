package heiil.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();

        helloLombok.setName("임승하");
        helloLombok.setAge(23);

        System.out.println(helloLombok.getName() + helloLombok.getAge());
    }

}
