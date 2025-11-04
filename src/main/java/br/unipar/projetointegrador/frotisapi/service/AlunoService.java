package br.unipar.projetointegrador.frotisapi.service;

import br.unipar.projetointegrador.frotisapi.model.Aluno;
import br.unipar.projetointegrador.frotisapi.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Adicionado import
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final PasswordEncoder passwordEncoder; // Adicionado o codificador de senha

    @Autowired
    // Construtor atualizado para receber o PasswordEncoder
    public AlunoService(AlunoRepository alunoRepository, PasswordEncoder passwordEncoder) {
        this.alunoRepository = alunoRepository;
        this.passwordEncoder = passwordEncoder; // Injetado
    }

    // Método 'salvar' atualizado para codificar a senha
    public Aluno salvar(Aluno aluno) {
        // Pega a senha em texto puro que veio da requisição
        String senhaPura = aluno.getSenha();

        // Codifica a senha usando o BCryptPasswordEncoder
        String senhaCodificada = passwordEncoder.encode(senhaPura);

        // Define a senha já codificada de volta no objeto aluno antes de salvar
        aluno.setSenha(senhaCodificada);

        // Salva o aluno no banco de dados com a senha já no formato hash
        return alunoRepository.save(aluno);
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Aluno atualizar(Long id, Aluno alunoAtualizado) {
        Aluno alunoExistente = buscarPorId(id);

        if (alunoExistente != null) {
            // atualiza os campos do aluno existente com os valores do aluno atualizado
            alunoExistente.setNome(alunoAtualizado.getNome());
            alunoExistente.setEmail(alunoAtualizado.getEmail());
            alunoExistente.setTelefone(alunoAtualizado.getTelefone());
            // Nota: Se permitir atualizar a senha, a lógica de codificação também deve ser aplicada aqui.
            return alunoRepository.save(alunoExistente);
        } else {
            return null; // ou lance uma exceção, dependendo da sua lógica de negócios
        }
    }

    public Aluno buscarPorCpf(String cpf) {
        // O .orElse(null) retorna o aluno se encontrado, ou null se não for.
        return alunoRepository.findByCpf(cpf).orElse(null);
    }
}