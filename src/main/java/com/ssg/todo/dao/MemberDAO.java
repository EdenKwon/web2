package com.ssg.todo.dao;

import com.ssg.todo.domain.MemberVO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Log4j2
public class MemberDAO {

    public MemberVO getWithPassword(MemberVO vo) throws Exception {
        String sql = "select * from tbl_member where mid = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setString(1, vo.getMid());

        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();
        MemberVO memberVO = MemberVO.builder()
                .mid(rs.getString("mid"))
                .mpw(rs.getString("mpw"))
                .build();

        log.info("디비에서 가져온 vo" + memberVO);

        return memberVO;
    }
}
