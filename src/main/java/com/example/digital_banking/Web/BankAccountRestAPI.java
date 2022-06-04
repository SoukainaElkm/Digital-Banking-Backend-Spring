package com.example.digital_banking.Web;

import com.example.digital_banking.DTOs.AccountHistoryDTO;
import com.example.digital_banking.DTOs.AccountOperationsDTO;
import com.example.digital_banking.DTOs.BankAccountDTO;
import com.example.digital_banking.Exceptions.BankAccountNotFoundException;
import com.example.digital_banking.Service.BankAccountServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")

public class BankAccountRestAPI {
    private BankAccountServiceImpl bankAccountService;

    public BankAccountRestAPI(BankAccountServiceImpl bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts(){
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationsDTO> getHistory(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
                                               @RequestParam(name="page", defaultValue = "0") int page,
                                               @RequestParam(name="size", defaultValue = "5") int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }
}
