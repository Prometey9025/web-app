package com.graduate.graduatework.service;

import com.graduate.graduatework.dto.CourseDTO;
import com.graduate.graduatework.mapping.AccountMapper;
import com.graduate.graduatework.mapping.CourseMapper;
import com.graduate.graduatework.model.Course;
import com.graduate.graduatework.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final AccountMapper accountMapper;
    private final AccountService accountService;

    public List<CourseDTO> getEntities(){
        log.info("Getting all courses");
        return courseMapper.toDTOList(courseRepository.findAll());
    }

    public CourseDTO getEntity(Long id) {
        Course course = null;
        try {
            course = courseRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        log.info("Getting course by ID: {}", id);
        return courseMapper.toDTO(course);
    }

    public CourseDTO createEntity(CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        course.setAuthor(accountMapper.toEntity(accountService.getEntity(courseDTO.getAuthorId())));
        Course savedCourse = courseRepository.save(course);
        log.info("Creating course: {}", savedCourse);
        return courseMapper.toDTO(savedCourse);
    }

    public CourseDTO updateEntity(Long id, CourseDTO courseDTO) {
        Course existingCourse;
        try {
            existingCourse = courseRepository.findById(id).orElseThrow(() -> new Exception("Course not found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        existingCourse.setName(courseDTO.getName());
        existingCourse.setText(courseDTO.getText());
        log.info("Updating course: {}", existingCourse);
        return courseMapper.toDTO(courseRepository.save(existingCourse));
    }

    public void deleteEntity(Long id) {
        log.info("Deleting course with ID: {}", id);
        courseRepository.deleteById(id);
    }
}
