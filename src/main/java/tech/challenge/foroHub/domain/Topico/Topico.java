package tech.challenge.foroHub.domain.Topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.challenge.foroHub.domain.Curso.Curso;
import tech.challenge.foroHub.domain.Respuesta.Respuesta;
import tech.challenge.foroHub.domain.Usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    Usuario autor;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Respuesta> respuestas;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.estado = Estado.SIN_SOLUCION;


    }
}
