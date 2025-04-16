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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoTecnologia getTipo() {
        return tipo;
    }

    public void setTipo(TipoTecnologia tipo) {
        this.tipo = tipo;
    }

    public List<Idea> getIdeias() {
        return ideias;
    }

    public void setIdeias(List<Idea> ideias) {
        this.ideias = ideias;
    }
}
