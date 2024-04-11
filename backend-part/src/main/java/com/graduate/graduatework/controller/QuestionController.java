package com.graduate.graduatework.controller;

import com.graduate.graduatework.dto.QuestionDTO;
import com.graduate.graduatework.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@Api(tags = "Question API")
public class QuestionController {
    private final QuestionService questionService;

    @ApiOperation("getAllQuestions")
    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getEntities() {
        return ResponseEntity.ok(questionService.getEntities());
    }

    @ApiOperation("getQuestionById")
    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getEntity(@PathVariable Long id) {
        QuestionDTO questionDTO = questionService.getEntity(id);
        if (questionDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(questionDTO);
    }

    @ApiOperation("createQuestion")
    @PostMapping
    public ResponseEntity<QuestionDTO> createEntity(@RequestBody QuestionDTO questionDTO) {
        if (questionDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        QuestionDTO createdQuestion = questionService.createEntity(questionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }

    @ApiOperation("updateQuestion")
    @PutMapping("/{id}")
    public ResponseEntity<QuestionDTO> updateEntity(@PathVariable Long id, @RequestBody QuestionDTO questionDTO) {
        if (questionDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        QuestionDTO updatedQuestion = questionService.updateEntity(id, questionDTO);
        if (updatedQuestion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedQuestion);
    }

    @ApiOperation("deleteQuestion")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        questionService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
