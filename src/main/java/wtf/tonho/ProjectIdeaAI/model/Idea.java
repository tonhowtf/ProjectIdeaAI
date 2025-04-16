package wtf.tonho.ProjectIdeaAI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import wtf.tonho.ProjectIdeaAI.model.enums.Categoria;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "idea")
@NoArgsConstructor
@AllArgsConstructor
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @ManyToMany
    @JoinTable(
            name = "idea_tecnologia",
            joinColumns = @JoinColumn(name = "idea_id"),
            inverseJoinColumns = @JoinColumn(name = "tecnologia_id")
    )
    private List<Tecnologia> tecnologias;
    private LocalDateTime dataCriacao;
    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }


}
