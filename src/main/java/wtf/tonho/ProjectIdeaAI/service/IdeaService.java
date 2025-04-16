package wtf.tonho.ProjectIdeaAI.service;

import org.springframework.stereotype.Service;
import wtf.tonho.ProjectIdeaAI.model.Idea;
import wtf.tonho.ProjectIdeaAI.repository.IdeaRepository;

import java.util.List;
import java.util.Optional;

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

   public Optional<Idea> buscarPorId(Long id) {
        return ideaRepository.findById(id);
   }

   public Idea alterarPorId(Idea idea) {
        return ideaRepository.save(idea);
   }


    public void removerPorId(Long id) {
        ideaRepository.deleteById(id);
    }



}
