package com.ssg.todo.service;

import com.ssg.todo.dao.TodoDAO;
import com.ssg.todo.domain.TodoVO;
import com.ssg.todo.dto.TodoDTO;
import com.ssg.todo.util.ModelUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {
    INSTANCE;

    private TodoDAO dao;

    private ModelMapper modelMapper;

    TodoService() {
        dao = new TodoDAO(); //직접 dao 주입
        modelMapper = ModelUtil.INSTANCE.get();
    }

    public void register(TodoDTO dto) throws Exception {
        TodoVO vo = modelMapper.map(dto, TodoVO.class);

        log.info(vo);
        dao.insert(vo);    //int를 반환하므로 예외처리도 진행예정
    }

    public List<TodoDTO> listAll() throws Exception {
        List<TodoVO> voList = dao.selectAllList();
        log.info("..........................");
        log.info(voList);

        List<TodoDTO> dtoList = voList.stream().map(vo->modelMapper.map(vo,TodoDTO.class)).collect(Collectors.toList());
        return dtoList;

    }

    public TodoDTO get(Long tno) throws Exception {
        log.info("tno = " + tno);
        TodoVO todoVO = dao.selectOne(tno);
        TodoDTO dto = modelMapper.map(todoVO, TodoDTO.class);

        return dto;
    }

    public void modify(TodoDTO dto) throws Exception {
        log.info("todoDTO : " + dto);
        TodoVO vo = modelMapper.map(dto, TodoVO.class);
        dao.updateOne(vo);
    }

    public void delete(Long tno) throws Exception {
        dao.deleteOne(tno);
    }
}
