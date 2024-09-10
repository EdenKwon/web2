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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name="todoModifyController", value = "/todo/modify")
@Log4j2
public class TodoModifyController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    private final DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tno = Long.parseLong(req.getParameter("tno"));

        try {
            TodoDTO dto = todoService.get(tno);

            req.setAttribute("dto", dto);
            req.getRequestDispatcher("/todo/modify.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TodoDTO dto = TodoDTO.builder()
                        .tno(Long.parseLong(req.getParameter("tno")))
                        .title(req.getParameter("title"))
                        .dueDate(LocalDate.parse(req.getParameter("dueDate"),DATETIMEFORMATTER))
                        .finished(req.getParameter("finished") != null &&
                                req.getParameter("finished").equals("on")).build();

        log.info("todo Modify Controller ..............POST");
        log.info(dto);
        /*System.out.println(req.getParameter("finished"));
        System.out.println(Boolean.parseBoolean(req.getParameter("finished")));
*/
        try {
            todoService.modify(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/todo/list");
    }
}
