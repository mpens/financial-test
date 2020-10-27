package com.agileengine.financial.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.key.OffsetDateTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

  private String id;
  private TransactionType type;
  private double amount;
  @JsonDeserialize(as = OffsetDateTimeKeyDeserializer.class)
  @JsonSerialize(using = OffsetDateTimeSerializer.class)
  private OffsetDateTime effectiveDate;

  public Transactions(TransactionType type, double amount) {
    this.type = type;
    this.amount = amount;
  }
}
