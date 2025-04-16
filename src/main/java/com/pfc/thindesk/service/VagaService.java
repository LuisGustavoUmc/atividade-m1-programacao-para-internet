package com.pfc.thindesk.service;

import com.pfc.thindesk.entity.Vaga;
import com.pfc.thindesk.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    /**
     * Cria uma nova vaga e salva no banco de dados.
     * @return A vaga salva com ID gerado pelo MongoDB.
     */
    public Vaga criarVaga(Vaga vaga) {
        return vagaRepository.save(vaga);
    }

    /**
     * Retorna a lista completa de vagas cadastradas.
     * @return Lista de objetos do tipo Vaga.
     */
    public List<Vaga> listarTodasVagas() {
        return vagaRepository.findAll();
    }

    /**
     * Busca uma vaga específica pelo seu ID.
     * @return Um Optional contendo a vaga, caso encontrada.
     */
    public Optional<Vaga> buscarVagaPorId(String id) {
        return vagaRepository.findById(id);
    }

    /**
     * Atualiza os dados de uma vaga existente.
     * Verifica se a vaga existe antes de atualizar.
     * @return A vaga atualizada, ou null se não encontrada.
     */
    public Vaga atualizarVaga(String id, Vaga vaga) {
        if (vagaRepository.existsById(id)) {
            vaga.setId(id);
            return vagaRepository.save(vaga);
        } else {
            return null; // Retorna null se a vaga não existir
        }
    }

    /**
     * Remove uma vaga do banco de dados a partir do seu ID.
     */
    public void deletarVaga(String id) {
        vagaRepository.deleteById(id);
    }
}

