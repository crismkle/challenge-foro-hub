package tech.challenge.foroHub.domain.Posteo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tech.challenge.foroHub.domain.Usuario.Usuario;

import java.time.LocalDateTime;

@Table(name = "posteos")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class Posteo {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    String mensaje;
    LocalDateTime fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_posteo_usuario"))
    Usuario autor;
}
