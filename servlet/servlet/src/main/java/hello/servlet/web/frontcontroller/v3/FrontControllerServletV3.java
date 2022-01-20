package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormCotrollerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    public FrontControllerServletV3() {

        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("FrontControllerServletV2.service");
        // request에서 url 정보를 뺌
        String requestURI = request.getRequestURI();

        // 1. 컨트롤러 조회
        ControllerV3 controller = controllerV3Map.get(requestURI); // 이 부분이 다형성 (자식에다가 부모를 넣음. 역할과 객체 구분)
        if (controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 2. paramMap 제작
        Map<String, String> paramMap = createParamMap(request);
        // 3. paramMap을 개별 컨트롤러에 넘겨주면서 컨트롤러 호출, ModelView 반환
        ModelView mv = controller.process(paramMap);

        // 4. modelView에서 viewName 가져옴
        String viewName = mv.getViewName();
        // 5. 가져온 viewName에서 viewReslover로 완전한 Path 만들어서 MyView 반환
        MyView view = viewReslover(viewName);
        // 6.render(model) 호출
        view.render(mv.getModel(), request, response);
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
