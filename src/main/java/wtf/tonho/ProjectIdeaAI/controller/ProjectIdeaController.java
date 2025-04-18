package wtf.tonho.ProjectIdeaAI.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import wtf.tonho.ProjectIdeaAI.service.ChatGptService;

@RestController
public class ProjectIdeaController {

    private ChatGptService chatGptService;

    public ProjectIdeaController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateProject() {
        return chatGptService.generateProject().map(project -> ResponseEntity.ok(project)).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
