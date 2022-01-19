package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 상태 코드 설정
        response.setStatus(HttpServletResponse.SC_OK);

        // 헤더 정보 설정
        response.setHeader("Content-type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, mustrevalidate");
        response.setHeader("Pragm a", "no-cache");

        // 헤더 편의 메서드. 아래에 정의해 두었음
        content(response);
        cookie(response);
        redirect(response);


        // 응답 바디에 값 써주기
        PrintWriter writer = response.getWriter();
        writer.println("ok");

        

    }

    // 헤더 편의 메서드- Content. 여기서 Content 헤더값을 지정함
    private void content(HttpServletResponse response) {

        //Content-Type: text/plain;charset=utf-8, Content-Length: 2 로 설정해주고 싶음

        // 이렇게 해주거나
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");

        // 이렇게 써줘도 됨
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600; 이렇게 헤더를 써주려고 함

        // 아래 이 줄처럼 써줘도 되고
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");

        //이렇게 써주면 위와 똑같은 결과가 나옴
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302, Location: /basic/hello-form.html으로 해줄거임

        //아래 두 줄처럼 해주거나
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        
        //이 한 줄처럼 써줘도 ㄱㅊ
        response.sendRedirect("/basic/hello-form.html");
    }

}
