package br.com.mmt.helpdesk.resources.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request){

        String mensagem = String.format("Objeto não encontrado! ");
        StandartError standartError = new StandartError(LocalDateTime.now().toString(),
                HttpStatus.NOT_FOUND.value(),
                mensagem,
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standartError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandartError> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request){

        String mensagem = String.format("Houve uma violação de dados! ");
        StandartError standartError = new StandartError(LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                mensagem,
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standartError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandartError> validationErrors(MethodArgumentNotValidException ex, HttpServletRequest request){

        String mensagem = String.format("Houve um erro na informação dos dados! ");
        ValidationError validationError = new ValidationError(LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                mensagem,
                "Dados informados inválidos ou não informados!",
                request.getRequestURI());

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            validationError.addError(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

}
