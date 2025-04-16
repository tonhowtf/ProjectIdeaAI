package wtf.tonho.ProjectIdeaAI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import wtf.tonho.ProjectIdeaAI.model.enums.TipoTecnologia;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tecnologia")
@NoArgsConstructor
@AllArgsConstructor
public class Tecnologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoTecnologia tipo;

    @ManyToMany(mappedBy = "tecnologias")
    private List<Idea> ideias = new ArrayList<>();


}
