package com.agileengine.financial.services;

import com.agileengine.financial.controller.requests.TransactionRequest;
import com.agileengine.financial.exceptions.BadRequestException;
import com.agileengine.financial.exceptions.DataNotFoundException;
import com.agileengine.financial.model.TransactionType;
import com.agileengine.financial.model.Transactions;
import com.agileengine.financial.storage.VirtualDataBase;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private VirtualDataBase virtualDataBase;
  private LockService lockService;

  @Autowired
  public TransactionService(
      VirtualDataBase virtualDataBase, LockService lockService) {
    this.virtualDataBase = virtualDataBase;
    this.lockService = lockService;
  }

  public Transactions createTransaction(TransactionRequest transactionRequest) {

    final List<Transactions> allCredits = virtualDataBase
        .findAllByType(TransactionType.CREDIT);

    final List<Transactions> allDebits = virtualDataBase
        .findAllByType(TransactionType.DEBIT);

    final Double sumCredits = allCredits.stream()
        .map(Transactions::getAmount).mapToDouble(value -> value).sum();
    final Double sumDebit = allDebits.stream()
        .map(Transactions::getAmount).mapToDouble(value -> value).sum();

    double subTotal = sumCredits - sumDebit;

    if (transactionRequest.getType() == TransactionType.CREDIT) {
      subTotal += transactionRequest.getAmount();
    } else {
      subTotal -= transactionRequest.getAmount();
    }

    if (subTotal >= 0) {
      final Transactions transactions = new Transactions(
          transactionRequest.getType(),
          transactionRequest.getAmount());
      transactions.setEffectiveDate(OffsetDateTime.now());
      transactions.setId(UUID.randomUUID().toString());
      virtualDataBase.create(transactions);
      return transactions;
    } else {
      throw new BadRequestException("transaction_error",
          "This transaction is not allowed amount negative",
          HttpStatus.BAD_REQUEST);
    }

  }

  public List<Transactions> getAllTransactions() {
    return virtualDataBase.findAll();
  }

  public Transactions getTransaction(String id) {

    final Optional<Transactions> byId = virtualDataBase.get(id);
    return byId.orElseThrow(() -> new DataNotFoundException("transaction_not_found",
        "the id wasn't found in the database",
        HttpStatus.NOT_FOUND));
  }

  public Transactions updateTransaction(String id, TransactionRequest transactionRequest) {
    lockService.lock(id);
    final Optional<Transactions> transactionsOptional = virtualDataBase.get(id);
    final Transactions transaction = transactionsOptional
        .orElseThrow(() -> {
          lockService.unlock(id);
          return new DataNotFoundException("transaction_not_found",
              "the id wasn't found in the database",
              HttpStatus.NOT_FOUND);
        });
    transaction.setAmount(transactionRequest.getAmount());
    transaction.setType(transactionRequest.getType());
    virtualDataBase.update(transaction);
    lockService.lock(id);
    return transaction;
  }
}
