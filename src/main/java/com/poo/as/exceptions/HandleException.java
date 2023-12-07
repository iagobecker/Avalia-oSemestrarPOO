package com.poo.as.exceptions;

import com.poo.as.dtos.erro.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class HandleException {

    @ExceptionHandler(NotFoundCliente.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundCliente(NotFoundCliente exception) {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage(), new ArrayList<>()));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidException(MethodArgumentNotValidException exception) {
        List<String> listOfErrors = new ArrayList<>();

        for (int i = 0; i < exception.getFieldErrors().size(); i++) {
            listOfErrors.add(exception.getFieldErrors().get(i).getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(exception.getDetailMessageCode(), listOfErrors));
    }
}
