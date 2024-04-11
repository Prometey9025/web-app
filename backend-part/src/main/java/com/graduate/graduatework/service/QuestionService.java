package com.graduate.graduatework.service;

import com.graduate.graduatework.dto.QuestionDTO;
import com.graduate.graduatework.mapping.ContestMapper;
import com.graduate.graduatework.mapping.QuestionMapper;
import com.graduate.graduatework.model.Question;
import com.graduate.graduatework.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final ContestMapper contestMapper;
    private final ContestService contestService;

    public List<QuestionDTO> getEntities(){
        log.info("Getting all questions");
        return questionMapper.toDTOList(questionRepository.findAll());
    }

    public QuestionDTO getEntity(Long id) {
        Question question;
        try {
            question = questionRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        log.info("Getting question by ID: {}", id);
        return questionMapper.toDTO(question);
    }

    public QuestionDTO createEntity(QuestionDTO questionDTO) {
        Question question = questionMapper.toEntity(questionDTO);
        question.setContest(contestMapper.toEntity(contestService.getEntity(questionDTO.getContestId())));
        Question savedQuestion = questionRepository.save(question);
        log.info("Creating question: {}", savedQuestion);
        return questionMapper.toDTO(savedQuestion);
    }

    public QuestionDTO updateEntity(Long id, QuestionDTO questionDTO) {
        Question existingQuestion;
        try {
            existingQuestion = questionRepository.findById(id).orElseThrow(() -> new Exception("Question not found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        existingQuestion.setCorrectAnswer(questionDTO.getCorrectAnswer());
        existingQuestion.setText(questionDTO.getText());
        log.info("Updating question: {}", existingQuestion);
        return questionMapper.toDTO(questionRepository.save(existingQuestion));
    }

    public void deleteEntity(Long id) {
        log.info("Deleting question with ID: {}", id);
        questionRepository.deleteById(id);
    }
}
