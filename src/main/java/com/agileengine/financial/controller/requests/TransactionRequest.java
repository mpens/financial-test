package com.agileengine.financial.controller.requests;

import com.agileengine.financial.model.TransactionType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.OffsetTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetTimeSerializer;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class TransactionRequest {
  private TransactionType type;
  private double amount;
}
