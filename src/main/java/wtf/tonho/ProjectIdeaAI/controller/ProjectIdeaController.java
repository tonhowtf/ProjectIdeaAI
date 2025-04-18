package wtf.tonho.ProjectIdeaAI.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import wtf.tonho.ProjectIdeaAI.model.Idea;
import wtf.tonho.ProjectIdeaAI.service.ChatGptService;
import wtf.tonho.ProjectIdeaAI.service.IdeaService;

import java.util.List;

@RestController
public class ProjectIdeaController {

    private IdeaService ideaService;
    private ChatGptService chatGptService;


    public ProjectIdeaController(IdeaService ideaService, ChatGptService chatGptService) {
        this.ideaService = ideaService;
        this.chatGptService = chatGptService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateProject() {
        List<Idea> ideas = ideaService.buscarTodas();
        return chatGptService.generateProject(ideas).map(project -> ResponseEntity.ok(project)).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
