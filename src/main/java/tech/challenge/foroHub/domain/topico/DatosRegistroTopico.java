package tech.challenge.foroHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico (
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String nombreCurso,
        @NotNull
        Long usuario_id
) {

        public DatosRegistroTopico(String titulo, String mensaje, String nombreCurso, Long usuario_id){
                this.titulo = titulo;
                this.mensaje = mensaje;
                this.nombreCurso = nombreCurso;
                this.usuario_id = usuario_id;
        }
}
