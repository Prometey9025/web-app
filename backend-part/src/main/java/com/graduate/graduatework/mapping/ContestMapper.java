package com.graduate.graduatework.mapping;

import com.graduate.graduatework.dto.ContestDTO;
import com.graduate.graduatework.model.Contest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContestMapper {
    private final ModelMapper modelMapper;

    public ContestDTO toDTO(Contest contest) {
        return modelMapper.map(contest, ContestDTO.class);
    }

    public Contest toEntity(ContestDTO contestDTO) {
        return modelMapper.map(contestDTO, Contest.class);
    }

    public List<ContestDTO> toDTOList(List<Contest> contests) {
        return contests.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Contest> toEntityList(List<ContestDTO> contestDTOs) {
        return contestDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
