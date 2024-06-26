package tech.challenge.foroHub.domain.respuesta;


import tech.challenge.foroHub.domain.topico.DatosListadoTopico;

import java.time.LocalDateTime;

public record DatosListadoRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fecha_creacion,
        DatosListadoTopico topico,
        String autor,
        Boolean solucion
) {
    public DatosListadoRespuesta(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFecha_creacion(),
                new DatosListadoTopico(respuesta.getTopico()), respuesta.getAutor().getNombre(), respuesta.getSolucion());
    }
}
