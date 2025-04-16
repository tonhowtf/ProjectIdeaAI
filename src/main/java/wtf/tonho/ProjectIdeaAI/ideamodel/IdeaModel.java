package wtf.tonho.ProjectIdeaAI.ideamodel;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tech_item")
@NoArgsConstructor
@AllArgsConstructor

public class IdeaModel {

    private Long id;
    private String nome;
    private String descricao;
    private String categoria;
    private List<Tecnologia> tecnologias;
    private LocalDateTime dataCriacao;



}
