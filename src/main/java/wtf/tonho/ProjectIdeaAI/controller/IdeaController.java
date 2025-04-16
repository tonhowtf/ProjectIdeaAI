package wtf.tonho.ProjectIdeaAI.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wtf.tonho.ProjectIdeaAI.model.Idea;
import wtf.tonho.ProjectIdeaAI.repository.IdeaRepository;
import wtf.tonho.ProjectIdeaAI.service.IdeaService;

import java.util.List;

@RestController
@RequestMapping("/idea")
public class IdeaController {

    private IdeaService ideaService;

    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Idea> criar(@RequestBody Idea idea) {
        Idea salvo = ideaService.salvar(idea);
        return ResponseEntity.ok(salvo);
    }

    // Get
    @GetMapping("listar")
    public ResponseEntity<List<Idea>> buscarTodas(){
        List<Idea> todos = ideaService.buscarTodas();
        return ResponseEntity.ok(todos);
    }

    /*@GetMapping("listar/{id}")
    public ResponseEntity<Idea> buscarPorId(@PathVariable Long id) {


    }*/

    // Update
    @PutMapping("alterar/{id}")
    public ResponseEntity<Idea> alterarPorId(@PathVariable Long id, @RequestBody Idea idea) {
       return ideaService.buscarPorId(id).map(ideaExistente -> {
            idea.setId(ideaExistente.getId());
            Idea atualizado = ideaService.alterarPorId(idea);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        ideaService.removerPorId(id);
        return ResponseEntity.noContent().build();
    }


}
