package tech.challenge.foroHub.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechacreacion,
        String estado,
        String curso,
        String autor
) {

    public DatosListadoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechacreacion(),
                topico.getEstado().toString(), topico.getCurso().getNombre(), topico.getAutor().getNombre());
    }

}
