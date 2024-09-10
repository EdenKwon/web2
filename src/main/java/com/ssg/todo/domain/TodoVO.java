package com.ssg.todo.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TodoVO {
    private long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
