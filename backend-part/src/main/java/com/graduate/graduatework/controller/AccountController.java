package com.graduate.graduatework.controller;

import com.graduate.graduatework.dto.AccountDTO;
import com.graduate.graduatework.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Api(tags = "Account API")
public class AccountController {
    private final AccountService accountService;

    @ApiOperation("getAllAccounts")
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getEntities() {
        return ResponseEntity.ok(accountService.getEntities());
    }

    @ApiOperation("getAccountById")
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getEntity(@PathVariable Long id) {
        AccountDTO accountDTO = accountService.getEntity(id);
        if (accountDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountDTO);
    }

    @ApiOperation("createAccount")
    @PostMapping
    public ResponseEntity<AccountDTO> createEntity(@RequestBody AccountDTO accountDTO) {
        if (accountDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        AccountDTO createdAccount = accountService.createEntity(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @ApiOperation("updateAccountById")
    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> updateEntity(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        if (accountDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        AccountDTO updatedAccount = accountService.updateEntity(id, accountDTO);
        if (updatedAccount == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAccount);
    }

    @ApiOperation("deleteAccountById")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        accountService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }

}
