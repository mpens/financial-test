package com.agileengine.financial.storage;

import com.agileengine.financial.model.TransactionType;
import com.agileengine.financial.model.Transactions;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class VirtualDataBase {

  private Map<String, Transactions> store = new ConcurrentHashMap<>();

  public Optional<Transactions> get(String id) {
    return Optional.ofNullable(store.get(id));
  }

  public Transactions create(Transactions transactions) {
    Objects.requireNonNull(transactions.getId(), "id must to be a string value");
    if (store.get(transactions.getId()) != null) {
      throw new RuntimeException("Error saving in Database - Duplicated Id");
    }
    store.put(transactions.getId(), transactions);
    return transactions;
  }

  public List<Transactions> findAllByType(TransactionType type) {
    return store.values().stream().filter(transactions -> transactions.getType() == type)
        .collect(
            Collectors.toList());
  }

  public List<Transactions> findAll() {
    return new ArrayList<>(store.values());
  }

  public Transactions update(Transactions transaction) {
    store.put(transaction.getId(), transaction);
    return transaction;
  }
}
