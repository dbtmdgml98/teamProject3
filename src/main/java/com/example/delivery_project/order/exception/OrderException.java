package com.example.delivery_project.order.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderException  extends RuntimeException {

   private ErrorCode errorCode;
   private String errorMessage;

   public OrderException(ErrorCode errorCode) {
       super(errorCode.getErrorMessage());
       this.errorCode = errorCode;
       this.errorMessage = errorCode.getErrorMessage();
   }
}
