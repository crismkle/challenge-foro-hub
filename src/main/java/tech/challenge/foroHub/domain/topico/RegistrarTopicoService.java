package tech.challenge.foroHub.domain.topico;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.challenge.foroHub.infra.errores.ValidacionDeIntegridad;

import java.util.List;

@Service
public class RegistrarTopicoService {

    @Autowired
    public TopicoRepository topicoRepository;

    public void registrar(DatosRegistroTopico datosRegistroTopico){

        List<Topico> topicos = topicoRepository.findByTitulo(datosRegistroTopico.titulo());
        topicos.stream()
                .filter(t -> t.getMensaje().equalsIgnoreCase(datosRegistroTopico.mensaje()))
                .findFirst()
                .ifPresent(t -> {
                    throw new ValidacionDeIntegridad("No se pueden crear tópicos duplicados");
                });

    }

}
