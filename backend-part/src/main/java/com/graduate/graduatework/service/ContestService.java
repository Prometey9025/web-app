package com.graduate.graduatework.service;

import com.graduate.graduatework.dto.ContestDTO;
import com.graduate.graduatework.mapping.AccountMapper;
import com.graduate.graduatework.mapping.ContestMapper;
import com.graduate.graduatework.model.Contest;
import com.graduate.graduatework.repository.ContestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContestService {
    private final ContestRepository contestRepository;
    private final ContestMapper contestMapper;
    private final AccountMapper accountMapper;
    private final AccountService accountService;

    public List<ContestDTO> getEntities() {
        log.info("Getting all contests");
        return contestMapper.toDTOList(contestRepository.findAll());
    }

    public ContestDTO getEntity(Long id) {
        Contest contest = null;
        try {
            contest = contestRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        log.info("Getting contest by ID: {}", id);
        return contestMapper.toDTO(contest);
    }

    public ContestDTO createEntity(ContestDTO contestDTO) {
        Contest contest = contestMapper.toEntity(contestDTO);
        contest.setAuthor(accountMapper.toEntity(accountService.getEntity(contestDTO.getAuthorId())));
        Contest savedContest = contestRepository.save(contest);
        log.info("Creating contest: {}", savedContest);
        return contestMapper.toDTO(savedContest);
    }

    public ContestDTO updateEntity(Long id, ContestDTO contestDTO) {
        Contest existingContest;
        try {
            existingContest = contestRepository.findById(id).orElseThrow(() -> new Exception("Contest not found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        existingContest.setName(contestDTO.getName());
        log.info("Updating contest: {}", existingContest);
        return contestMapper.toDTO(contestRepository.save(existingContest));
    }

    public void deleteEntity(Long id) {
        log.info("Deleting contest with ID: {}", id);
        contestRepository.deleteById(id);
    }
}
