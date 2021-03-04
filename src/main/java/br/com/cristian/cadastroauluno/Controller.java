package br.com.cristian.cadastroauluno;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class Controller {

    private final List<Alunos> alunos;


    public Controller() {
        this.alunos = new ArrayList<>();

    }

    @GetMapping
    public List<Alunos> Controller(){
        return alunos;
    }
}
