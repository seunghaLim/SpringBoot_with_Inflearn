package heiil.core;
import heiil.core.member.Grade;
import heiil.core.member.Member;
import heiil.core.member.MemberService;
import heiil.core.order.Order;
import heiil.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

//        기존코드 - OrderApp에서 직접 객체를 생성함
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        // 바뀐코드 - appConfig가 대신 객체를 생성하고 주입시켜줌
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();
        
        //스프링 컨테이너까지 쓰려면 필요한 코드
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
        System.out.println("discountPrice: " + order.calculatePrice());
    }
}