package com.scaler.ProductService.controlleradvice;

import com.scaler.ProductService.dtos.ExceptionDto;
import com.scaler.ProductService.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    //only pass the message which data type is String
//    public ResponseEntity<String> handleArithmeticException() {
//            ResponseEntity<String> response = new ResponseEntity<>(
//                    "Something went wrong",
//                    HttpStatus.NOT_FOUND
//            );
//            return response;
//    }
    //OR

    //here we pass a object which is defined as ExceptionDto
    public ResponseEntity<ExceptionDto> handleArithmeticException() {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Arithmetic exception has happend");
        exceptionDto.setSolution("I don't know, please try again");
        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(
               exceptionDto,
                HttpStatus.BAD_REQUEST
        );
        return response;
    }


    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<String> handleArrayIndexOutOfBoundsException() {
        ResponseEntity<String> response = new ResponseEntity<>(
                "ArrayIndexOutOfBoundsException has happened, Inside the ControllerAdvice.",
                HttpStatus.BAD_REQUEST
        );

        return response;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException() {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("product not found");
        exceptionDto.setSolution("Please try again with a valid product id");
        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(
                exceptionDto,
                HttpStatus.BAD_REQUEST
        );
        return response;
    }


//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<String> handleNullPointerException() {
//        ResponseEntity<String> response = new ResponseEntity<>(
//                "NullPointerException has happened.",
//                HttpStatus.BAD_REQUEST
//        );
//
//        return response;
//    }
}
