package com.graduate.graduatework.controller;

import com.graduate.graduatework.dto.ContestDTO;
import com.graduate.graduatework.service.ContestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contests")
@RequiredArgsConstructor
@Api(tags = "Contest API")
public class ContestController {
    private final ContestService contestService;

    @ApiOperation("getAllContests")
    @GetMapping
    public ResponseEntity<List<ContestDTO>> getEntities() {
        return ResponseEntity.ok(contestService.getEntities());
    }

    @ApiOperation("getContestById")
    @GetMapping("/{id}")
    public ResponseEntity<ContestDTO> getEntity(@PathVariable Long id) {
        ContestDTO contestDTO = contestService.getEntity(id);
        if (contestDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contestDTO);
    }

    @ApiOperation("createContest")
    @PostMapping
    public ResponseEntity<ContestDTO> createEntity(@RequestBody ContestDTO contestDTO) {
        if (contestDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        ContestDTO createdContest = contestService.createEntity(contestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContest);
    }

    @ApiOperation("updateContest")
    @PutMapping("/{id}")
    public ResponseEntity<ContestDTO> updateEntity(@PathVariable Long id, @RequestBody ContestDTO contestDTO) {
        if (contestDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        ContestDTO updatedContest = contestService.updateEntity(id, contestDTO);
        if (updatedContest == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedContest);
    }

    @ApiOperation("deleteContest")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        contestService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
