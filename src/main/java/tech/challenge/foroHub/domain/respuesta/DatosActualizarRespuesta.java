package tech.challenge.foroHub.domain.respuesta;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(
        String mensaje,
        Long topico_id,
        Long usuario_id,
        Boolean solucion
) {
}
