package com.graduate.graduatework.dto;

import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;
    private String text;
    private String correctAnswer;
    private Long contestId;
}
