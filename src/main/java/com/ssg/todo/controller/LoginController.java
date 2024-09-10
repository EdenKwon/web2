package com.ssg.todo.controller;

import com.ssg.todo.dto.MemberDTO;
import com.ssg.todo.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet(name="loginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    //doGet은 로그인 화면을 보여주고 doPost는 실제 로그인 처리하도록 구상하도록 한다.
    //1. webServlet 해당 컨트롤러 등록 이름
    //2. doGet login.jsp파일로 포워딩
    //3. login.jsp 파일 만들기 ..text 2개 id id(mid), pwd(mpw), submit 버튼

    MemberService memberService = MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/todo/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*        User user = User.builder()
                .mid(req.getParameter("mid"))
                .mpw(req.getParameter("mpw"))
                .build();*/

        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");

        MemberDTO dto = MemberDTO.builder()
                .mid(mid)
                .mpw(mpw)
                .build();

        try {
            memberService.login(dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String str = mid + mpw;

        HttpSession session = req.getSession(); //HTTP세션을 이용해서 setAttribute()를 사용자 공간에 logininfo라는 이름으로 문자열 보관
        session.setAttribute("loginInfo", str);

        //req.setAttribute("user", user);
        resp.sendRedirect("/todo/list");
    }
}
