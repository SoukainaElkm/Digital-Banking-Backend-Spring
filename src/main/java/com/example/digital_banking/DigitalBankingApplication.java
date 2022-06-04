package com.example.digital_banking;

import com.example.digital_banking.DTOs.BankAccountDTO;
import com.example.digital_banking.DTOs.CurrentBankAccountDTO;
import com.example.digital_banking.DTOs.CustomerDTO;
import com.example.digital_banking.DTOs.SavingBankAccountDTO;
import com.example.digital_banking.Exceptions.CustomerNotFound;
import com.example.digital_banking.Service.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService) {
        return args -> {
            Stream.of("Soukaina", "Salma", "Ihsan").forEach(name -> {
                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomers().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentAccount(Math.random() * 90000, 9000, customer.getId());
                    bankAccountService.saveSavingAccount(Math.random() * 120000, 5.5, customer.getId());


                } catch (CustomerNotFound e) {
                    e.printStackTrace();
                }
            });
            List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
            for (BankAccountDTO bankAccount : bankAccounts) {
                for (int i = 0; i < 10; i++) {
                    String accountId;
                    if (bankAccount instanceof SavingBankAccountDTO) {
                        accountId = ((SavingBankAccountDTO) bankAccount).getId();
                    } else {
                        accountId = ((CurrentBankAccountDTO) bankAccount).getId();

                    }
                    bankAccountService.credit(accountId, 100000 + Math.random() * 120000, "Credit");
                    bankAccountService.debit(accountId, 1000 + Math.random() * 90000, "Débit");
                }
            }
        };
    }

}
