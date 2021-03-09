package br.com.cristian.cadastroauluno.service;

import br.com.cristian.cadastroauluno.entity.Alunos;
import jdk.jfr.consumer.RecordedClassLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoService {

    private final List<Alunos> alunos;


    public AlunoService() {
        this.alunos = new ArrayList<>();

    }

    @GetMapping
    public List<Alunos> findAll(@RequestParam(required = false) String aluno){
        if(aluno != null) {
            return alunos.stream().filter(aln -> aln.getNome().contains(aluno))
                    .collect(Collectors.toList());
        }return alunos;
    }

    @GetMapping("/{id}")
    public Alunos findById(@PathVariable("id") Integer id){
        return this.alunos.stream().filter(aln -> aln.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Alunos aluno){
        if(aluno.getId()== null){
            aluno.setId(alunos.size() +1);
        }
        alunos.add(aluno);
        return new ResponseEntity<>(aluno.getId(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Alunos aluno){
        alunos.stream().filter(aln -> aln.getId().equals(aluno.getId()))
                .forEach(aln -> aln.setNome(aluno.getNome()));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        alunos.removeIf(aln->aln.getId().equals(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}