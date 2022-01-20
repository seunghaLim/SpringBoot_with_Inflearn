package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerV4Map = new HashMap<>();

    public FrontControllerServletV4() {

        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("FrontControllerServletV2.service");
        // request에서 url 정보를 뺌
        String requestURI = request.getRequestURI();

        // 1. 컨트롤러 조회
        ControllerV4 controller = controllerV4Map.get(requestURI); // 이 부분이 다형성 (자식에다가 부모를 넣음. 역할과 객체 구분)
        if (controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 2. paramMap와 model 제작
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        // 3. paramMap과 model을 개별 컨트롤러에 넘겨주면서 컨트롤러 호출, 문자열 반환
        String viewName = controller.process(paramMap, model);

        // 5. 가져온 viewName에서 viewReslover로 완전한 Path 만들어서 MyView 반환
        MyView view = viewReslover(viewName);

        // 6.render(model) 호출
        view.render(model, request, response);
    }

    // /WEB-INF/views/members.jsp 제작 즉 실제 물리 뷰 경로 제작
    private MyView viewReslover(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");

    }

    //request에서 파라미터 전부 뽑아서 맵으로 만들어줌
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
