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
import java.util.List;

@WebServlet(name="todoListController", urlPatterns = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("todo...list.....");

        /*List<TodoVO> dtoList = null;
        try {
            dtoList = TodoService.INSTANCE.getList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("dtolist",dtoList);*/

        try {
            List<TodoDTO> dtoList = todoService.listAll();
            req.setAttribute("dtolist", dtoList);
            req.getRequestDispatcher("/todo/list.jsp").forward(req,resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException();
        }

    }


}
