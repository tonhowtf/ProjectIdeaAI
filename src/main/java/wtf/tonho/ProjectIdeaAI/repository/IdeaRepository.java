package wtf.tonho.ProjectIdeaAI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wtf.tonho.ProjectIdeaAI.model.Idea;

public interface IdeaRepository extends JpaRepository<Idea, Long> {
}
