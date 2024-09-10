package com.ssg.todo.service;

import com.ssg.todo.dao.MemberDAO;
import com.ssg.todo.domain.MemberVO;
import com.ssg.todo.dto.MemberDTO;
import com.ssg.todo.util.ModelUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

@Log4j2
public enum MemberService {

    INSTANCE;

    MemberDAO memberDAO;

    private ModelMapper modelMapper;

    MemberService() {
        memberDAO = new MemberDAO();
        modelMapper = ModelUtil.INSTANCE.get();
    }

    public void login(MemberDTO dto) throws Exception {
        MemberVO vo = modelMapper.map(dto,MemberVO.class);
        MemberVO dbVo = memberDAO.getWithPassword(vo);

        log.info("사용자가 입력한 vo" + vo);
        if(!vo.getMpw().equals(dbVo.getMpw())) {
            log.info("비밀번호 불일치");
            throw new Exception();
        } else {
            log.info("비밀번호 일치. 지나가세요");
        }
    }
}
