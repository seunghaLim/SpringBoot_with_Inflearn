package hello.hellosprint.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


// 컨트롤러, 서비스, 리파지토리 실행 시 각각의 시간을 측정하는 로직
@Aspect //얘도 필요
@Component // 스프링 빈으로 등록해줘야 함
public class TimeTraceAop {

    @Around("execution(* hello.hellosprint.. *(..))") // 야래 코드를 어디에다가 적용할 지 그 영역을 정해주어야 함
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{

        long start = System.currentTimeMillis(); // 시작시간
        System.out.println("START: " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis(); // 끝난 시간
            long timeMs = finish - start; // 접속하는 데 걸린 시간
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
