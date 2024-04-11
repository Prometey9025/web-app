package com.graduate.graduatework.controller;

import com.graduate.graduatework.dto.AccountContestRelationshipDTO;
import com.graduate.graduatework.service.AccountContestRelationshipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relationshipsAccountContest")
@RequiredArgsConstructor
@Api(tags = "AccountContestRelationship API")
public class AccountContestRelationshipController {
    private final AccountContestRelationshipService relationshipService;

    @ApiOperation("getAllRelationshipsAccountContest")
    @GetMapping
    public ResponseEntity<List<AccountContestRelationshipDTO>> getEntities() {
        return ResponseEntity.ok(relationshipService.getEntities());
    }

    @ApiOperation("getRelationshipsAccountContestById")
    @GetMapping("/{accountId}/{contestId}")
    public ResponseEntity<AccountContestRelationshipDTO> getEntity(
            @PathVariable Long accountId, @PathVariable Long contestId) {
        AccountContestRelationshipDTO relationshipDTO = relationshipService.getEntity(accountId, contestId);
        if (relationshipDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(relationshipDTO);
    }

    @ApiOperation("createRelationshipsAccountContest")
    @PostMapping
    public ResponseEntity<AccountContestRelationshipDTO> createRelationship(@RequestBody AccountContestRelationshipDTO relationshipDTO) {
        if (relationshipDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        AccountContestRelationshipDTO createdRelationship = relationshipService.createEntity(relationshipDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRelationship);
    }

    @ApiOperation("deleteRelationshipsAccountContest")
    @DeleteMapping("/{accountId}/{contestId}")
    public ResponseEntity<Void> deleteRelationship(
            @PathVariable Long accountId, @PathVariable Long contestId) {
        relationshipService.deleteEntity(accountId, contestId);
        return ResponseEntity.noContent().build();
    }
}
