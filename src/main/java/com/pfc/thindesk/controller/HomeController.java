package com.pfc.thindesk.controller;

import com.pfc.thindesk.entity.Vaga;
import com.pfc.thindesk.service.VagaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private VagaService vagaService;

    /**
     * Mostra a página de login.
     * Essa rota é usada pelo Spring Security quando alguém tenta acessar algo sem estar logado.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Página inicial do sistema.
     */
    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("layout");
        String fragment = "home :: content"; // Indica o fragmento que será carregado dentro do layout
        log.info("Carregando fragmento: {}", fragment);
        modelAndView.addObject("content", fragment);
        return modelAndView;
    }

    /**
     * Mostra a lista de todas as vagas cadastradas.
     */
    @GetMapping("/vagas")
    public String listarVagas(Model model) {
        List<Vaga> vagas = vagaService.listarTodasVagas();
        model.addAttribute("vagas", vagas);
        String fragment = "vagas :: content";
        log.info("Carregando fragmento: {}", fragment);
        model.addAttribute("content", fragment);
        return "vagas";
    }

    /**
     * Mostra o formulário para criar uma nova vaga.
     */
    @GetMapping("/vagas/novo")
    public String novoFormularioVaga(Model model) {
        model.addAttribute("vaga", new Vaga());
        return "novo-vaga";
    }

    /**
     * Mostra o formulário para editar uma vaga já existente.
     */
    @GetMapping("/vagas/editar/{id}")
    public String editarFormularioVaga(@PathVariable("id") String id, Model model) {
        Vaga vaga = vagaService.buscarVagaPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada: " + id));
        model.addAttribute("vaga", vaga);
        return "editar-vaga";
    }

    /**
     * Salva uma nova vaga cadastrada via formulário.
     * Depois de salvar, redireciona para a lista de vagas.
     */
    @PostMapping("/vagas/salvar")
    public String salvarVaga(@ModelAttribute Vaga vaga) {
        vagaService.criarVaga(vaga);
        return "redirect:/vagas";
    }

    /**
     * Remove uma vaga do sistema com base no ID.
     */
    @PostMapping("/vagas/deletar")
    public String deletarVaga(@RequestParam("id") String id) {
        vagaService.deletarVaga(id);
        return "redirect:/vagas";
    }

    /**
     * Atualiza os dados de uma vaga existente.
     */
    @PostMapping("/vagas/atualizar/{id}")
    public String atualizarVaga(@PathVariable("id") String id, @ModelAttribute("vaga") Vaga vaga) {
        vaga.setId(id);
        vagaService.atualizarVaga(id, vaga);
        return "redirect:/vagas";
    }
}
