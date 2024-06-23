package tech.challenge.foroHub.domain.topico.validaciones;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.challenge.foroHub.domain.topico.DatosRegistroTopico;
import tech.challenge.foroHub.domain.topico.Topico;
import tech.challenge.foroHub.domain.topico.TopicoRepository;
import tech.challenge.foroHub.infra.errores.ValidacionDeIntegridad;

import java.util.List;

@Component
public class TopicosNoDuplicados implements ValidadorDeTopicos {

    @Autowired
    public TopicoRepository topicoRepository;

    public void validar(DatosRegistroTopico datosRegistroTopico){

        List<Topico> topicos = topicoRepository.findByTitulo(datosRegistroTopico.titulo());
        topicos.stream()
                .filter(t -> t.getMensaje().equalsIgnoreCase(datosRegistroTopico.mensaje()))
                .findFirst()
                .ifPresent(t -> {
                    throw new ValidationException("No se pueden crear tópicos duplicados");
                });

    }

}
