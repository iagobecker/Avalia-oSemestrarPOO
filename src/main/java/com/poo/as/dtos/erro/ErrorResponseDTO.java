package com.poo.as.dtos.erro;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ErrorResponseDTO {

    public  ErrorResponseDTO(String message, List<String> erros) {
        this.message = message;
        this.erros = erros;
    }

    private String message;
    private List<String> erros;
    private LocalTime date = LocalTime.now();
}
