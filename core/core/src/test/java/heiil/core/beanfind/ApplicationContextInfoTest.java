package heiil.core.beanfind;

import heiil.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        //스프링에 등록된 모든 빈 이름을 조회함 - getBeanDefinitionNames()
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        // 리스트같은 for문 쓸 껀덕지가 있음 - iter 치고 탭
        for (String beanDefinitionName : beanDefinitionNames) {
            //  getBean 메소드에 빈 이름(beanDefinitionName)을 넣어서 해당 이름의 빈을 꺼내옴 (bean에 담김)
            Object bean = ac.getBean(beanDefinitionName);
            // 빈 이름과 빈 객체 자체
            System.out.println("name= " + beanDefinitionName + " object=" + bean);
        }
    }

    // 내가 올려달라고 한 빈만출력 (빈 데피니션이 롤 어플리케이션인 것만 출력)
    @Test
    @DisplayName("모든 빈 출력하기")
    void findApplicationBean(){

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {

            // beanDefinition - 빈에 대한 정보. 메타데이터 이걸 꺼냄
            // ROLE_APPLICATION : 일반적으로 사용자가 정의한 빈
            // ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name= " + beanDefinitionName + " object=" + bean);
            }
        }
    }




}
