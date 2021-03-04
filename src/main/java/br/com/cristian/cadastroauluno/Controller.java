package br.com.cristian.cadastroauluno;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class Controller {

    private final List<Alunos> alunos;


    public Controller() {
        this.alunos = new ArrayList<>();

    }

    @GetMapping
    public List<Alunos> findAll(@RequestParam(required = false) String aluno){
        if(aluno != null) {
            return alunos.stream().filter(aln -> aln.getNome().contains(aluno))
                    .collect(Collectors.toList());
        }return alunos;
    }

    @PostMapping
    public Integer add(@RequestBody Alunos aluno){
        if(aluno.getId()== null){
            aluno.setId(alunos.size() +1);
        }
        alunos.add(aluno);
        return aluno.getId();
    }

    @PutMapping
    public void update(@RequestBody Alunos aluno){
        alunos.stream().filter(aln -> aln.getId().equals(aluno.getId()))
                .forEach(aln -> aln.setNome(aluno.getNome()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        alunos.removeIf(aln->aln.getId().equals(id));
    }

}
