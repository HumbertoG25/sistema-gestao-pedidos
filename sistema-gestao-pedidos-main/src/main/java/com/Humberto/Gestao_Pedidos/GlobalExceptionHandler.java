package com.Humberto.Gestao_Pedidos;

import org . springframework . http . HttpStatus ;
import org . springframework . http . ResponseEntity ;
import org . springframework . validation . FieldError ;
import org . springframework . web . bind . MethodArgumentNotValidException ;
import org . springframework . web . bind . annotation . ExceptionHandler ;
import org . springframework . web . bind . annotation . RestControllerAdvice ;

import java . util . HashMap ;
import java . util . Map ;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> tratarErroValidação(MethodArgumentNotValidException ex ){
        Map<String, String> erros = new HashMap<>();

        for (FieldError erro : ex.getBindingResult().getFieldErrors()){
            erros.put(erro.getField(), erro.getDefaultMessage());
        }

        ErroResposta resposta = new ErroResposta(HttpStatus.BAD_REQUEST.value(), "Erro de validação", erros);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}
