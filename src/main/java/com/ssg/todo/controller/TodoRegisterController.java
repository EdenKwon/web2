package com.ssg.todo.controller;


import com.ssg.todo.dto.TodoDTO;
import com.ssg.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@Log4j2
@WebServlet(name="todoRegisterController", urlPatterns="/todo/register")
public class TodoRegisterController extends HttpServlet {

    TodoService todoService = TodoService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        if(session.isNew()) { //기존에 JSESSIONID가 없는 경우
            log.info("JSESSIONID 쿠키가 새로 생성된 사용자입니다.");
            resp.sendRedirect("/login");
            return;
        } else {
            log.info("old session...............");
        }

        if(session.getAttribute("loginInfo") == null) {
            log.info("로그인 정보가 없는 사용자 입니다.");
            resp.sendRedirect("/login");
        }

        req.getRequestDispatcher("/todo/register.jsp").forward(req,resp);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/todo/register.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("입력 처리 후 리스트로 이동");
        TodoDTO dto = TodoDTO.builder()
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate")))
                .build();

        log.info("/info.register post...............");
        log.info(dto);

        try {
            todoService.register(dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/todo/list");
    }
}
