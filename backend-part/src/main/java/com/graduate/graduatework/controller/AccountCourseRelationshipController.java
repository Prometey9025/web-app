package com.graduate.graduatework.controller;

import com.graduate.graduatework.dto.AccountCourseRelationshipDTO;
import com.graduate.graduatework.service.AccountCourseRelationshipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relationshipsAccountCourse")
@RequiredArgsConstructor
@Api(tags = "AccountCourseRelationship API")
public class AccountCourseRelationshipController {
    private final AccountCourseRelationshipService relationshipService;

    @ApiOperation("getAllRelationshipsAccountCourse")
    @GetMapping
    public ResponseEntity<List<AccountCourseRelationshipDTO>> getEntities() {
        return ResponseEntity.ok(relationshipService.getEntities());
    }

    @ApiOperation("getRelationshipsAccountCourseById")
    @GetMapping("/{accountId}/{courseId}")
    public ResponseEntity<AccountCourseRelationshipDTO> getEntity(
            @PathVariable Long accountId, @PathVariable Long courseId) {
        AccountCourseRelationshipDTO relationshipDTO = relationshipService.getEntity(accountId, courseId);
        if (relationshipDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(relationshipDTO);
    }

    @ApiOperation("createRelationshipsAccountCourse")
    @PostMapping
    public ResponseEntity<AccountCourseRelationshipDTO> createRelationship(@RequestBody AccountCourseRelationshipDTO relationshipDTO) {
        if (relationshipDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        AccountCourseRelationshipDTO createdRelationship = relationshipService.createEntity(relationshipDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRelationship);
    }

    @ApiOperation("deleteRelationshipsAccountCourse")
    @DeleteMapping("/{accountId}/{courseId}")
    public ResponseEntity<Void> deleteRelationship(
            @PathVariable Long accountId, @PathVariable Long courseId) {
        relationshipService.deleteEntity(accountId, courseId);
        return ResponseEntity.noContent().build();
    }
}
