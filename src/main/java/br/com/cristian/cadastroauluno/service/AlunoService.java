package br.com.cristian.cadastroauluno.service;

import br.com.cristian.cadastroauluno.entity.Aluno;
import br.com.cristian.cadastroauluno.exceptions.NotFoundExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoService {


    private final List<Aluno> alunos;


    public AlunoService() {
        this.alunos = new ArrayList<>();

    }

    @GetMapping
    public List<Aluno> findAll(@RequestParam(required = false) String aluno){
        if(aluno == null){
            throw new NotFoundExceptions();
        }
        if(aluno != null) {
            return alunos.stream().filter(aln -> aln.getNome().contains(aluno))
                    .collect(Collectors.toList());
        }return alunos;

    }


    @GetMapping("/{id}")
    public Aluno findById(@PathVariable("id") String id){
        if(id != null ){
            throw new NotFoundExceptions();
        }else {
            return this.alunos.stream().filter(aln -> aln.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }
    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Aluno aluno){
        if(aluno.getId()== null){
            aluno.setId(alunos.size() +1);
        }
        alunos.add(aluno);
        return new ResponseEntity<>(aluno.getId(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Aluno aluno){
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