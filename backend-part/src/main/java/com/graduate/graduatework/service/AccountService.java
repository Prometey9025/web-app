package com.graduate.graduatework.service;

import com.graduate.graduatework.dto.AccountDTO;
import com.graduate.graduatework.mapping.AccountMapper;
import com.graduate.graduatework.model.Account;
import com.graduate.graduatework.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public List<AccountDTO> getEntities() {
        log.info("Getting all accounts");
        return accountMapper.toDTOList( accountRepository.findAll());
    }

    public AccountDTO getEntity(Long id) {
        Account account;
        try {
            account = accountRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        log.info("Getting account by ID: {}", id);
        return accountMapper.toDTO(account);
    }

    public AccountDTO createEntity(AccountDTO accountDTO) {
        Account savedAccount = accountRepository.save(accountMapper.toEntity(accountDTO));
        log.info("Creating account: {}", savedAccount);
        return accountMapper.toDTO(savedAccount);
    }

    public AccountDTO updateEntity(Long id, AccountDTO accountDTO) {
        Account existingAccount;
        try {
            existingAccount = accountRepository.findById(id).orElseThrow(() -> new Exception("Account not found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        existingAccount.setFirstName(accountDTO.getFirstName());
        existingAccount.setLastName(accountDTO.getLastName());
        existingAccount.setEmail(accountDTO.getEmail());
        log.info("Updating account: {}", existingAccount);
        return accountMapper.toDTO(accountRepository.save(existingAccount));
    }

    public void deleteEntity(Long id) {
        log.info("Deleting account with ID: {}", id);
        accountRepository.deleteById(id);
    }
}
