package tech.challenge.foroHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import tech.challenge.foroHub.domain.curso.Curso;
import tech.challenge.foroHub.domain.curso.CursoRepository;
import tech.challenge.foroHub.domain.topico.validaciones.ValidadorDeTopicos;
import tech.challenge.foroHub.domain.usuario.Usuario;
import tech.challenge.foroHub.domain.usuario.UsuarioRepository;
import tech.challenge.foroHub.infra.errores.ValidacionDeIntegridad;

import java.net.URI;
import java.util.List;

@Service
public class TopicoService {

    @Autowired
    public TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    List<ValidadorDeTopicos> validadores;

    public ResponseEntity<DatosRespuestaTopico> registrar(DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){

        if (cursoRepository.findByNombreContainsIgnoreCase(datosRegistroTopico.nombreCurso()).isEmpty()){
            throw new ValidacionDeIntegridad("El curso no fue encontrado");
        }

        if (usuarioRepository.findById(datosRegistroTopico.usuario_id()).isEmpty()){
            throw new ValidacionDeIntegridad("El usuario no fue encontrado");
        }

        validadores.forEach(v -> v.validar(datosRegistroTopico));

        var topico = new Topico(datosRegistroTopico);
        topico.setCurso(cursoRepository.findByNombreContainsIgnoreCase(datosRegistroTopico.nombreCurso()).get());
        topico.setAutor(usuarioRepository.findById(datosRegistroTopico.usuario_id()).get());
        Topico topicoRet = topicoRepository.save(topico);

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topicoRet.getId(), topicoRet.getTitulo(),
                topicoRet.getMensaje(), topicoRet.getFechacreacion().toString(), topicoRet.getEstado().toString(),
                topicoRet.getCurso().getId(), topicoRet.getAutor().getId());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoRet.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    public ResponseEntity<Page> listarTopicos(Pageable paginacion){

        return ResponseEntity.ok(topicoRepository.listarTopicos(paginacion)
                .map(DatosListadoTopico::new));
    }

    public ResponseEntity<DatosListadoTopico> listarDetalleTopicos(Long id){

        if (topicoRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El tópico no fue encontrado. Verifique el id.");
        }

        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosListadoTopico(topico);

        return ResponseEntity.ok(datosTopico);
    }


    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(DatosActualizarTopico datosActualizarTopico,
                                                                 Long id, UriComponentsBuilder uriComponentsBuilder) {

        Curso curso = null;
        Usuario usuario = null;

        if (topicoRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El tópico no fue encontrado. Verifique el id.");
        }

        if (datosActualizarTopico.nombreCurso() != null) {
            if (cursoRepository.findByNombreContainsIgnoreCase(datosActualizarTopico.nombreCurso()).isEmpty()) {
                throw new ValidacionDeIntegridad("El curso no fue encontrado");
            }
            curso = cursoRepository.findByNombreContainsIgnoreCase(datosActualizarTopico.nombreCurso()).get();
        }

        if (datosActualizarTopico.usuario_id() != null) {
            if (usuarioRepository.findById(datosActualizarTopico.usuario_id()).isEmpty()) {
                throw new ValidacionDeIntegridad("El usuario no fue encontrado");
            }
            usuario = usuarioRepository.findById(datosActualizarTopico.usuario_id()).get();
        }

        Topico topico = topicoRepository.getReferenceById(id);
        DatosRegistroTopico datosRegistroTopico = realizarCopiaActualizado(topico, datosActualizarTopico);

        validadores.forEach(v -> v.validar(datosRegistroTopico));

        topico.actualizarDatos(datosActualizarTopico, curso, usuario);

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechacreacion().toString(), topico.getEstado().toString(),
                topico.getCurso().getId(), topico.getAutor().getId());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaTopico);

    }

    private DatosRegistroTopico realizarCopiaActualizado(Topico topico, DatosActualizarTopico datosActualizarTopico) {
        String titulo = topico.getTitulo();
        String mensaje = topico.getMensaje();
        String nombreCurso = topico.getCurso().getNombre();
        Long usuario_id = topico.getAutor().getId();

        if(datosActualizarTopico.titulo() != null){
            titulo = datosActualizarTopico.titulo();
        }
        if(datosActualizarTopico.mensaje() != null){
            mensaje = datosActualizarTopico.mensaje();
        }
        if(datosActualizarTopico.nombreCurso() != null){
            nombreCurso = datosActualizarTopico.nombreCurso();
        }
        if(datosActualizarTopico.usuario_id() != null){
            usuario_id = datosActualizarTopico.usuario_id();
        }

        DatosRegistroTopico datosRegistroTopico = new DatosRegistroTopico(titulo, mensaje, nombreCurso, usuario_id);
        return datosRegistroTopico;
    }

    public ResponseEntity eliminarTopico(Long id) {

        if (topicoRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El tópico no fue encontrado. Verifique el id.");
        }

        topicoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
