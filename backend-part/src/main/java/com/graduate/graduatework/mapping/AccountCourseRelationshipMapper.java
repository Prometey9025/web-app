package com.graduate.graduatework.mapping;

import com.graduate.graduatework.dto.AccountCourseRelationshipDTO;
import com.graduate.graduatework.model.AccountCourseRelationship;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccountCourseRelationshipMapper {
    private final ModelMapper modelMapper;

    public AccountCourseRelationshipDTO toDTO(AccountCourseRelationship relationship) {
        return modelMapper.map(relationship, AccountCourseRelationshipDTO.class);
    }

    public AccountCourseRelationship toEntity(AccountCourseRelationshipDTO relationshipDTO) {
        return modelMapper.map(relationshipDTO, AccountCourseRelationship.class);
    }

    public List<AccountCourseRelationshipDTO> toDTOList(List<AccountCourseRelationship> relationships) {
        return relationships.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<AccountCourseRelationship> toEntityList(List<AccountCourseRelationshipDTO> relationshipDTO) {
        return relationshipDTO.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
