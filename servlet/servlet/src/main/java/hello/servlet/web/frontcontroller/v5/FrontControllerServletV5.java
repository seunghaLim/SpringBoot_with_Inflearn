package hello.servlet.web.frontcontroller.v5;


import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adaptor.ControllerV3HandlerAdaptor;
import hello.servlet.web.frontcontroller.v5.adaptor.ControllerV4HandlerAdaptor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FrontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    public void initHandlerMappingMap() {

        // ControllerV3 관련 URL 매핑
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());


        // ControllerV4 관련 URL 매핑
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    // 여기에 쓰고싶은 어댑터를 넣으면 됨 지금은 V3를 지원하는 어댑터가 들어가있음
    public void initHandlerAdapters(){
        // ControllerV3HandlerAdaptor 추가
        handlerAdapters.add(new ControllerV3HandlerAdaptor());

        // ControllerV4HandlerAdaptor 추가
        handlerAdapters.add(new ControllerV4HandlerAdaptor());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 핸들러 찾아옴
        Object handler = getHandler(request);
        if (handler == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 핸들러 어댑터 찾아옴
        MyHandlerAdapter adaptor = getHandlerAdaptor(handler);

        // 어댑터를 통해 modelView 객체를 가져옴(어댑터 안에서 호출된 컨트롤러가 만들어서 어댑터한테 주면 그걸 다시 프론트 컨트롤러가 받는거)
        ModelView mv = adaptor.handler(request, response, handler);

        // 2. paramMap 제작
        Map<String, String> paramMap = createParamMap(request);

        // 4. modelView에서 viewName 가져옴
        String viewName = mv.getViewName();
        // 5. 가져온 viewName에서 viewReslover로 완전한 Path 만들어서 MyView 반환
        MyView view = viewReslover(viewName);
        // 6.render(model) 호출
        view.render(mv.getModel(), request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdaptor(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            // 어댑터가 핸들러를 지원하는지 판단. 지원하면 해당 어댑터 반환
            if (adapter.supports(handler)){
                return adapter;
            }
        }

        throw new IllegalArgumentException("handler adaptor를 찾을 수 없습니다. handler = " + handler);
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
