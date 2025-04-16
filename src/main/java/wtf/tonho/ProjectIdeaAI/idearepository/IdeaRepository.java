package wtf.tonho.ProjectIdeaAI.idearepository;

import org.springframework.data.jpa.repository.JpaRepository;
import wtf.tonho.ProjectIdeaAI.ideamodel.IdeaModel;

public interface IdeaRepository extends JpaRepository<IdeaModel, Long> {
}
