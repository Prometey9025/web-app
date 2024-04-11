package com.graduate.graduatework.mapping;

import com.graduate.graduatework.dto.CourseDTO;
import com.graduate.graduatework.model.Course;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseMapper {
    private final ModelMapper modelMapper;

    public CourseDTO toDTO(Course course) {
        return modelMapper.map(course, CourseDTO.class);
    }

    public Course toEntity(CourseDTO courseDTO) {
        return modelMapper.map(courseDTO, Course.class);
    }

    public List<CourseDTO> toDTOList(List<Course> courses) {
        return courses.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Course> toEntityList(List<CourseDTO> courseDTOs) {
        return courseDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
