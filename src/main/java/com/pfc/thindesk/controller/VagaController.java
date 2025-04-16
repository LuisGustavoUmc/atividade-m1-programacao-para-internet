package com.pfc.thindesk.controller;

import com.pfc.thindesk.entity.Vaga;
import com.pfc.thindesk.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaga")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    /**
     * Cria uma nova vaga.
     */
    @PostMapping
    public Vaga criarVaga(@RequestBody Vaga vaga) {
        return vagaService.criarVaga(vaga);
    }

    /**
     * Retorna a lista de todas as vagas cadastradas.
     */
    @GetMapping
    public List<Vaga> listarVagas() {
        return vagaService.listarTodasVagas();
    }

    /**
     * Busca uma vaga específica pelo seu ID.
     * Se a vaga for encontrada, retorna com status 200 (OK), senão retorna 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vaga> buscarVagaPorId(@PathVariable String id) {
        return vagaService.buscarVagaPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Atualiza os dados de uma vaga existente.
     * Se a vaga existir, atualiza e retorna 200 (OK), senão retorna 404 (Not Found).
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vaga> atualizarVaga(@PathVariable String id, @RequestBody Vaga vaga) {
        Vaga vagaAtualizada = vagaService.atualizarVaga(id, vaga);
        if (vagaAtualizada != null) {
            return ResponseEntity.ok(vagaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Remove uma vaga a partir do seu ID.
     * Retorna 204 (No Content) se a exclusão ocorrer com sucesso.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVaga(@PathVariable String id) {
        vagaService.deletarVaga(id);
        return ResponseEntity.noContent().build();
    }
}
