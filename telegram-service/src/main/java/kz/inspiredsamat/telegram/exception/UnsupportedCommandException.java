package kz.inspiredsamat.telegram.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnsupportedCommandException extends RuntimeException {

  public UnsupportedCommandException(String message) {
    super(message);
  }
}
