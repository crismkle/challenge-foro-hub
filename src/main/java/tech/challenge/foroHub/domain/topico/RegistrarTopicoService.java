package tech.challenge.foroHub.domain.topico;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import tech.challenge.foroHub.domain.curso.CursoRepository;
import tech.challenge.foroHub.domain.topico.validaciones.ValidadorDeTopicos;
import tech.challenge.foroHub.domain.usuario.UsuarioRepository;
import tech.challenge.foroHub.infra.errores.ValidacionDeIntegridad;

import java.net.URI;
import java.util.List;

@Service
public class RegistrarTopicoService {

    @Autowired
    public TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    List<ValidadorDeTopicos> validadores;

    public ResponseEntity<DatosRespuestaTopico>  registrar(DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){

        if (cursoRepository.findByNombreContainsIgnoreCase(datosRegistroTopico.nombreCurso()).isEmpty()){
            throw new ValidacionDeIntegridad("El curso no fue encontrado");
        }

        if (usuarioRepository.findById(datosRegistroTopico.usuario_id()).isEmpty()){
            throw new ValidacionDeIntegridad("El usuario no fue encontrado");
        }
        System.out.println("Entra");
        validadores.forEach(v -> v.validar(datosRegistroTopico));

        var topico = new Topico(datosRegistroTopico);
        topico.setCurso(cursoRepository.findByNombreContainsIgnoreCase(datosRegistroTopico.nombreCurso()).get());
        topico.setAutor(usuarioRepository.findById(datosRegistroTopico.usuario_id()).get());
        Topico topicoRet = topicoRepository.save(topico);

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topicoRet.getId(), topicoRet.getTitulo(),
                topicoRet.getMensaje(), topicoRet.getFecha_creacion().toString(), topicoRet.getEstado().toString(),
                topicoRet.getCurso().getId(), topicoRet.getAutor().getId());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoRet.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

}
