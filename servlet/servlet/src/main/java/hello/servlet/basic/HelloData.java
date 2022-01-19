package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

//json 객체를 위해 만들어야함
@Getter @Setter
public class HelloData {

    private String username;
    private int age;

}
