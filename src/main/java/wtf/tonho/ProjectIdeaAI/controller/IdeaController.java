package wtf.tonho.ProjectIdeaAI.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wtf.tonho.ProjectIdeaAI.model.Idea;
import wtf.tonho.ProjectIdeaAI.repository.IdeaRepository;
import wtf.tonho.ProjectIdeaAI.service.IdeaService;

@RestController
@RequestMapping("/idea")
public class IdeaController {

    private IdeaService ideaService;

    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    // POST
    public ResponseEntity<Idea> criar(@RequestBody Idea idea) {
        Idea salvo = ideaService.salvar(idea);
        return ResponseEntity.ok(salvo);
    }

    // Get


    // Update

    // Delete


}
