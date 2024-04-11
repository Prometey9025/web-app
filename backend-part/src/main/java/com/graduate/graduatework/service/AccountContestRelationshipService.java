package com.graduate.graduatework.service;

import com.graduate.graduatework.dto.AccountContestRelationshipDTO;
import com.graduate.graduatework.mapping.AccountContestRelationshipMapper;
import com.graduate.graduatework.model.AccountContestRelationship;
import com.graduate.graduatework.model.key.AccountContestRelationshipKey;
import com.graduate.graduatework.repository.AccountContestRelationshipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountContestRelationshipService {
    private final AccountContestRelationshipRepository relationshipRepository;
    private final AccountContestRelationshipMapper relationshipMapper;

    public List<AccountContestRelationshipDTO> getEntities(){
        log.info("Getting all relationship");
        return relationshipMapper.toDTOList(relationshipRepository.findAll());
    }

    public AccountContestRelationshipDTO getEntity(Long accountId, Long contestId) {
        AccountContestRelationship relationship;
        try {
            relationship = relationshipRepository.findById(new AccountContestRelationshipKey(accountId, contestId)).orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        log.info("Getting relationship by accountId and contestId: {}", accountId + " " + contestId);
        return relationshipMapper.toDTO(relationship);
    }

    public AccountContestRelationshipDTO createEntity(AccountContestRelationshipDTO relationshipDTO) {
        AccountContestRelationship savedRelationship = relationshipRepository.save(relationshipMapper.toEntity(relationshipDTO));
        log.info("Creating relationship: {}", savedRelationship);
        return relationshipMapper.toDTO(savedRelationship);
    }

    public void deleteEntity(Long accountId, Long contestId) {
        log.info("Relationship deleted successfully with accountId and contestId: {}", accountId + " " + contestId);
        relationshipRepository.deleteById(new AccountContestRelationshipKey(accountId,contestId));
    }
}
