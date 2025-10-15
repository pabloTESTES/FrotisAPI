package br.unipar.projetointegrador.frotisapi.service;

import br.unipar.projetointegrador.frotisapi.model.Aluno;
import br.unipar.projetointegrador.frotisapi.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired; // 1. Importe a anotação
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository; // 2. Declare como 'final'

    /**
     * 3. Crie um construtor que recebe a dependência.
     * A anotação @Autowired informa ao Spring para "injetar" o AlunoRepository aqui.
     */
    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }

    public List<Aluno> listarTodos() {
        // Agora o alunoRepository não será mais null
        return alunoRepository.findAll();
    }

    public Aluno atualizar(Long id, Aluno alunoAtualizado) {
        Aluno alunoExistente = buscarPorId(id);

        if (alunoExistente != null) {
            // Atualiza os campos do aluno existente com os valores do aluno atualizado
            alunoExistente.setNome(alunoAtualizado.getNome());
            alunoExistente.setEmail(alunoAtualizado.getEmail());
            alunoExistente.setTelefone(alunoAtualizado.getTelefone());
            return alunoRepository.save(alunoExistente);
        } else {
            return null; // Ou lance uma exceção, dependendo da sua lógica de negócios
        }

    }


}