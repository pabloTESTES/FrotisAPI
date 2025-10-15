package br.unipar.projetointegrador.frotisapi.controller;

import br.unipar.projetointegrador.frotisapi.model.Exercicio;
import br.unipar.projetointegrador.frotisapi.service.ExercicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercicio")
public class ExercicioController {

    private ExercicioService exercicioService;

    public ExercicioController(ExercicioService exercicioService) {
        this.exercicioService = exercicioService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Exercicio>> listarExercicios() {
        List<Exercicio> exercicios = exercicioService.listarTodos();

        if (exercicios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(exercicios);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Exercicio> salvarExercicio(@RequestBody Exercicio exercicio) {
        Exercicio exercicioSalvo = exercicioService.salvar(exercicio);
        return ResponseEntity.status(201).body(exercicioSalvo);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Exercicio> buscarExercicioPorID(@PathVariable Long id) {
        Exercicio exercicio = exercicioService.buscarPorId(id);
        if (exercicio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercicio);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarExercicio(@PathVariable Long id) {
        exercicioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Exercicio> atualizarExercicio(@PathVariable Long id, @RequestBody Exercicio exercicioAtualizado) {
        Exercicio exercicio = exercicioService.atualizar(id, exercicioAtualizado);
        if (exercicio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercicio);

    }



}
