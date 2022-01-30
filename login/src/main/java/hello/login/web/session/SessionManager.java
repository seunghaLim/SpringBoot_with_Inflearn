package hello.login.web.session;


import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "mySessionId";
    private Map<String, Object> sessionStore = new HashMap<>();

    // 세션 저장
    public void createSession (Object value, HttpServletResponse response){

        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);
        Cookie MySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(MySessionCookie);
    }

    // 세션 조회
    public Object getSession(HttpServletRequest request){

        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);

        if (sessionCookie == null){
            return null;
        }

        return sessionStore.get(sessionCookie.getValue());
    }


    // 세션 만료
    public void expire (HttpServletRequest request){

        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie != null){
            sessionStore.remove(sessionCookie.getValue());
        }
    }



    // 쿠키 찾기
    private Cookie findCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null){
            return null;
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }


}
