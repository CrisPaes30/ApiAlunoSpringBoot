package br.com.cristian.cadastroauluno.exceptions;

import br.com.cristian.cadastroauluno.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/id")
public class AlunoExcepetions {
    AlunoService alunoService = new AlunoService();

    private AlunoExcepetions() {
    }

    public static AlunoExcepetions createAlunoException() {
        return new AlunoExcepetions();
    }

    @GetMapping
    public ResponseEntity<String> findName() {
        throw new NotFoundExceptions();

    }

    @GetMapping("{id}")
    public ResponseEntity<String> findId() {
        throw new NotFoundExceptions();
    }
}

