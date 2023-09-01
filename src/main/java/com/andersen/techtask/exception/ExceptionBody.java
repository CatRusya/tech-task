package com.andersen.techtask.exception;

import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionBody {
  String message;
  Map<String, String> errors;

  public ExceptionBody(String message) {
    this.message = message;
  }
}
