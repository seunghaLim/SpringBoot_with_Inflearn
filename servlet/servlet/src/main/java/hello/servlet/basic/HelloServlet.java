package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// /hello로 url이 오면 이 HelloServlet 실행
@WebServlet(name="helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // 단축키 컨트롤 + O
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("respond = " + response);

        // 요청에서 보낸 파라미터 값을 getParameter로 얻어올 수 있음
        String username = request.getParameter("username");
        System.out.println("username = " + username);


        // 응답에 원하는 값을 넣어서 반환 가능
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8"); // 이걸로 인코딩
        response.getWriter().write("hello " + username); // 이걸로 HTTP 바디에 메시지 적을 수 있음

    }
}
