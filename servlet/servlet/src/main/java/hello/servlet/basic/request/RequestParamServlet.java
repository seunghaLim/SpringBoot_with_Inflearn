package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("[전체 파라미터 조회] - start");

//        Enumeration<String> paramNames = request.getParameterNames();
//        while(paramNames.hasMoreElements()){
//            String paramName = paramNames.nextElement();
//            System.out.println( paramName + "= " + request.getParameter(paramName));
//
//        }

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName) ));
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();


        System.out.println("[단일 파라미터 조회]");
        String name = request.getParameter("username");
        System.out.println("request.getParameter(username) = " + name );

        String age = request.getParameter("age");
        System.out.println("request.getParameter(age) = " + age );
        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = request.getParameterValues("username");
        for (String username : usernames){
            System.out.println("username = " + username);
        }


    }


}
