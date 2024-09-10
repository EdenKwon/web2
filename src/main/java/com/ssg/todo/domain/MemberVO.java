package com.ssg.todo.domain;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    private String mid;
    private String mpw;
    private String mname;
}
