package tech.challenge.foroHub.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import tech.challenge.foroHub.domain.curso.CursoRepository;
import tech.challenge.foroHub.domain.topico.*;
import tech.challenge.foroHub.domain.usuario.UsuarioRepository;

import java.net.URI;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RegistrarTopicoService service;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){

        service.registrar(datosRegistroTopico);

        var topico = new Topico(datosRegistroTopico);
        // Puede ser nulo
        topico.setCurso(cursoRepository.findByNombreContainsIgnoreCase(datosRegistroTopico.nombreCurso()).get());
        // Puede ser nulo
        topico.setAutor(usuarioRepository.findById(datosRegistroTopico.usuario_id()).get());
        Topico topicoRet = topicoRepository.save(topico);


        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topicoRet.getId(), topicoRet.getTitulo(),
                topicoRet.getMensaje(), topicoRet.getFecha_creacion().toString(), topicoRet.getEstado().toString(),
                topicoRet.getCurso().getId(), topicoRet.getAutor().getId());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoRet.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaTopico);

    }





}


