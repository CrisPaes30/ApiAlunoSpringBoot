package br.com.cristian.cadastroauluno.controller;

import br.com.cristian.cadastroauluno.exceptions.NotFoundExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericControllerAdvice extends Throwable {

    Logger LOGGER = LoggerFactory.getLogger(GenericControllerAdvice.class);

    @ExceptionHandler({NotFoundExceptions.class})
    public ResponseEntity<String> handle(final NotFoundExceptions e) {
        final String recursoInexistente = "Nome ou Id não encontrado";
        LOGGER.error(recursoInexistente);
        return new ResponseEntity<>(recursoInexistente,HttpStatus.NOT_FOUND);
    }
}
