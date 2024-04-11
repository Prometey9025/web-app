package com.graduate.graduatework.mapping;

import com.graduate.graduatework.dto.AccountContestRelationshipDTO;
import com.graduate.graduatework.model.AccountContestRelationship;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccountContestRelationshipMapper {

    private final ModelMapper modelMapper;

    public AccountContestRelationshipDTO toDTO(AccountContestRelationship relationship) {
        return modelMapper.map(relationship, AccountContestRelationshipDTO.class);
    }

    public AccountContestRelationship toEntity(AccountContestRelationshipDTO relationshipDTO) {
        return modelMapper.map(relationshipDTO, AccountContestRelationship.class);
    }

    public List<AccountContestRelationshipDTO> toDTOList(List<AccountContestRelationship> relationships) {
        return relationships.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<AccountContestRelationship> toEntityList(List<AccountContestRelationshipDTO> relationshipDTO) {
        return relationshipDTO.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
