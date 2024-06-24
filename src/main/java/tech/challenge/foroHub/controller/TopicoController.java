package tech.challenge.foroHub.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import tech.challenge.foroHub.domain.curso.CursoRepository;
import tech.challenge.foroHub.domain.topico.*;
import tech.challenge.foroHub.domain.usuario.UsuarioRepository;


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
    private TopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){

        return  service.registrar(datosRegistroTopico, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page> listadoTopicos(@PageableDefault(size = 10)Pageable paginacion){

        return service.listarTopicos(paginacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> listarDetalleTopico(@PathVariable Long id){

        return service.listarDetalleTopicos(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizaTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico, @PathVariable Long id, UriComponentsBuilder uriComponentsBuilder){

        return service.actualizarTopico(datosActualizarTopico, id, uriComponentsBuilder);
    }

}


