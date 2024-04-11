package com.graduate.graduatework.service;

import com.graduate.graduatework.dto.AccountCourseRelationshipDTO;
import com.graduate.graduatework.mapping.AccountCourseRelationshipMapper;
import com.graduate.graduatework.model.AccountCourseRelationship;
import com.graduate.graduatework.model.key.AccountCourseRelationshipKey;
import com.graduate.graduatework.repository.AccountCourseRelationshipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountCourseRelationshipService {
    private final AccountCourseRelationshipRepository relationshipRepository;

    private final AccountCourseRelationshipMapper relationshipMapper;

    public List<AccountCourseRelationshipDTO> getEntities(){
        log.info("Getting all relationship");
        return relationshipMapper.toDTOList(relationshipRepository.findAll());
    }

    public AccountCourseRelationshipDTO getEntity(Long accountId, Long courseId) {
        AccountCourseRelationship relationship;
        try {
            relationship = relationshipRepository.findById(new AccountCourseRelationshipKey(accountId, courseId)).orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        log.info("Getting relationship by accountId and contestId: {}", accountId + " " + courseId);
        return relationshipMapper.toDTO(relationship);
    }

    public AccountCourseRelationshipDTO createEntity(AccountCourseRelationshipDTO relationshipDTO) {
        AccountCourseRelationship savedRelationship = relationshipRepository.save(relationshipMapper.toEntity(relationshipDTO));
        log.info("Creating relationship: {}", savedRelationship);
        return relationshipMapper.toDTO(savedRelationship);
    }

    public void deleteEntity(Long accountId, Long courseId) {
        log.info("Relationship deleted successfully with accountId and courseId: {}", accountId + " " + courseId);
        relationshipRepository.deleteById(new AccountCourseRelationshipKey(accountId,courseId));
    }
}
