package br.unipar.projetointegrador.frotisapi.service;

import br.unipar.projetointegrador.frotisapi.model.Treino;
import br.unipar.projetointegrador.frotisapi.repository.TreinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinoService {

    private TreinoRepository treinoRepository;

    public TreinoService(TreinoRepository treinoRepository) {
        this.treinoRepository = treinoRepository;
    }

    public Treino Salvar(Treino treino) {
        return treinoRepository.save(treino);
    }

    public List<Treino> findAll() {
        return treinoRepository.findAll();
    }

    public void deleteById(Long id) {
        treinoRepository.deleteById(id);
    }

    public Treino findById(Long id) {
        return treinoRepository.findById(id).orElse(null);
    }


}
