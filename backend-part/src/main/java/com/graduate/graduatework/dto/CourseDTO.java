package com.graduate.graduatework.dto;

import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private String name;
    private String text;
    private Long authorId;
}
