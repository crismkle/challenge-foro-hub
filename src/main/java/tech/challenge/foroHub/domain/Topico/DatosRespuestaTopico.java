package tech.challenge.foroHub.domain.Topico;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String fecha_creacion,
        String estado,
        Long curso_id,
        Long usuario_id
) {
}
