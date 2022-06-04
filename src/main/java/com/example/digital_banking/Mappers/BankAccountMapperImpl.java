package com.example.digital_banking.Mappers;

import com.example.digital_banking.DTOs.AccountOperationsDTO;
import com.example.digital_banking.DTOs.CurrentBankAccountDTO;
import com.example.digital_banking.DTOs.CustomerDTO;
import com.example.digital_banking.DTOs.SavingBankAccountDTO;
import com.example.digital_banking.Entities.AccountOperations;
import com.example.digital_banking.Entities.CurrentAccount;
import com.example.digital_banking.Entities.Customer;
import com.example.digital_banking.Entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

//Le mapper transfer les données d'un objet à un autre
//MapStruct: framework qui fait la même chose que BeanUtils

@Service
public class BankAccountMapperImpl {
    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO); // il fait le transfer des données du premier objet vers le deuxième
                                                        // équivalent à ce qui est au dessous
        /*customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());*/
        return customerDTO;
    }
    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }

    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount){
        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);
        savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }

    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingAccountDTO){
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDTO, savingAccount);
        savingAccount.setCustomer(fromCustomerDTO(savingAccountDTO.getCustomerDTO()));
        return savingAccount;
    }

    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount){
        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);
        currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
        currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentBankAccountDTO;
    }

    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentAccountDTO){
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDTO, currentAccount);
        currentAccount.setCustomer(fromCustomerDTO(currentAccountDTO.getCustomerDTO()));
        return currentAccount;
    }

    public AccountOperationsDTO fromAccountOperation(AccountOperations accountOperations){
        AccountOperationsDTO accountOperationsDTO = new AccountOperationsDTO();
        BeanUtils.copyProperties(accountOperations,accountOperationsDTO);
        return accountOperationsDTO;
    }

}
