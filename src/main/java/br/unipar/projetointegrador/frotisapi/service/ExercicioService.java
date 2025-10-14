package br.unipar.projetointegrador.frotisapi.service;

import br.unipar.projetointegrador.frotisapi.model.Exercicio;
import br.unipar.projetointegrador.frotisapi.repository.ExercicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercicioService {

    private ExercicioRepository exercicioRepository;

    public Exercicio salvar(Exercicio exercicio) {
        return exercicioRepository.save(exercicio);
    }

    public Exercicio buscarPorId(Long id) {
        return exercicioRepository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        exercicioRepository.deleteById(id);
    }

    public List<Exercicio> listarTodos() {
        return exercicioRepository.findAll();
    }

    public Exercicio atualizar(Long id, Exercicio exercicioAtualizado) {
        Exercicio exercicioExistente = buscarPorId(id);

        if (exercicioExistente != null) {
            // Atualiza os campos do exercício existente com os valores do exercício atualizado
            exercicioExistente.setNome(exercicioAtualizado.getNome());
            exercicioExistente.setSeries(exercicioAtualizado.getSeries());
            exercicioExistente.setRepeticoes(exercicioAtualizado.getRepeticoes());
            return exercicioRepository.save(exercicioExistente);
        } else {
            return null; // Ou lance uma exceção, dependendo da sua lógica de negócios
        }

    }
}
