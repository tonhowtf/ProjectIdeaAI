package wtf.tonho.ProjectIdeaAI.service;

import org.springframework.stereotype.Service;
import wtf.tonho.ProjectIdeaAI.model.Idea;
import wtf.tonho.ProjectIdeaAI.repository.IdeaRepository;

import java.util.List;

@Service
public class IdeaService {

    private IdeaRepository ideaRepository;
    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    public Idea salvar(Idea idea){
        return ideaRepository.save(idea);
    }

    public List<Idea> buscarTodas(){
        return ideaRepository.findAll();
    }
}
