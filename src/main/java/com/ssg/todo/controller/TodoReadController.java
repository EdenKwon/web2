package com.ssg.todo.controller;

import com.ssg.todo.dto.TodoDTO;
import com.ssg.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="todoReadController", urlPatterns = "/todo/read")
@Log4j2
public class TodoReadController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("todo/read.............실행");
        Long tno = Long.parseLong(req.getParameter("tno"));

        try {
            TodoDTO dto = todoService.get(tno);

            req.setAttribute("dto", dto);
            req.getRequestDispatcher("/todo/read.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("Read Page error");
        }

    }
}
