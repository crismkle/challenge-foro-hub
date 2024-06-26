package tech.challenge.foroHub.domain.respuesta;

import tech.challenge.foroHub.domain.topico.DatosListadoTopico;

import java.time.LocalDateTime;

public record DatosRetornoRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fecha_creacion,
        DatosListadoTopico topico,
        String autor,
        Boolean solucion
) {
}
