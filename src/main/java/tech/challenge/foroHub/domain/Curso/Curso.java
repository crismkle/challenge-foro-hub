package tech.challenge.foroHub.domain.Curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tech.challenge.foroHub.domain.Topico.Topico;

import java.util.List;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}
