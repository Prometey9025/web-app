package com.graduate.graduatework.controller;

import com.graduate.graduatework.dto.CourseDTO;
import com.graduate.graduatework.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Api(tags = "Course API")
public class CourseController {
    private final CourseService courseService;

    @ApiOperation("getAllCourses")
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getEntities() {
        return ResponseEntity.ok(courseService.getEntities());
    }

    @ApiOperation("getCourseById")
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getEntity(@PathVariable Long id) {
        CourseDTO courseDTO = courseService.getEntity(id);
        if (courseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courseDTO);
    }

    @ApiOperation("createCourse")
    @PostMapping
    public ResponseEntity<CourseDTO> createEntity(@RequestBody CourseDTO courseDTO) {
        if (courseDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        CourseDTO createdCourse = courseService.createEntity(courseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @ApiOperation("updateCourse")
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateEntity(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        if (courseDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        CourseDTO updatedCourse = courseService.updateEntity(id, courseDTO);
        if (updatedCourse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCourse);
    }

    @ApiOperation("deleteCourse")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        courseService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
