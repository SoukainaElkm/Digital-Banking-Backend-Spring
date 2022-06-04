package com.example.digital_banking.Repositories;

import com.example.digital_banking.Entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
