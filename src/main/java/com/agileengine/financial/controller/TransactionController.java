package com.agileengine.financial.controller;

import com.agileengine.financial.controller.requests.TransactionRequest;
import com.agileengine.financial.model.Transactions;
import com.agileengine.financial.services.TransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {

  private TransactionService transactionService;

  @Autowired
  public TransactionController(
      TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping("/")
  public ResponseEntity<List<Transactions>> getTransactions() {
    return ResponseEntity.ok(transactionService.getAllTransactions());
  }

  @PostMapping("/")
  public ResponseEntity<Transactions> createTransactions(
      @RequestBody TransactionRequest transactionRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(transactionService.createTransaction(transactionRequest));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Transactions> update(
      @PathVariable String id,
      @RequestBody TransactionRequest transactionRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(transactionService.updateTransaction(id,transactionRequest));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Transactions> showTransaction(@PathVariable String id) {
    return ResponseEntity.ok(transactionService.getTransaction(id));
  }
}
