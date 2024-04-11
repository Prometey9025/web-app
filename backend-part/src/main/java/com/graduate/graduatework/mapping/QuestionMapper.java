package com.graduate.graduatework.mapping;

import com.graduate.graduatework.dto.QuestionDTO;
import com.graduate.graduatework.model.Question;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionMapper {
    private final ModelMapper modelMapper;

    public QuestionDTO toDTO(Question question) {
        return modelMapper.map(question, QuestionDTO.class);
    }

    public Question toEntity(QuestionDTO questionDTO) {
        return modelMapper.map(questionDTO, Question.class);
    }

    public List<QuestionDTO> toDTOList(List<Question> questions) {
        return questions.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Question> toEntityList(List<QuestionDTO> questionDTOs) {
        return questionDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
