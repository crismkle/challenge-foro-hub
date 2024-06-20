package tech.challenge.foroHub.domain.Posteo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta extends Posteo {

    @ManyToOne
    @JoinColumn(name = "topico_id", foreignKey = @ForeignKey(name = "FK_respuesta_topico"))
    private Topico topico;

}
