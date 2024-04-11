package com.graduate.graduatework.mapping;

import com.graduate.graduatework.dto.AccountDTO;
import com.graduate.graduatework.model.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final ModelMapper modelMapper;

    public AccountDTO toDTO(Account account) {
        return modelMapper.map(account, AccountDTO.class);
    }

    public Account toEntity(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, Account.class);
    }

    public List<AccountDTO> toDTOList(List<Account> accounts) {
        return accounts.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Account> toEntityList(List<AccountDTO> accountDTOs) {
        return accountDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
