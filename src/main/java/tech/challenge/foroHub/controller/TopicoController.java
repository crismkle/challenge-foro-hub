package tech.challenge.foroHub.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.challenge.foroHub.domain.Curso.CursoRepository;
import tech.challenge.foroHub.domain.Topico.DatosRegistroTopico;
import tech.challenge.foroHub.domain.Topico.Topico;
import tech.challenge.foroHub.domain.Topico.TopicoRepository;
import tech.challenge.foroHub.domain.Usuario.UsuarioRepository;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico){

        var topico = new Topico(datosRegistroTopico);
        topico.setCurso(cursoRepository.findByNombreContainsIgnoreCase(datosRegistroTopico.nombreCurso()).get());
        topico.setAutor(usuarioRepository.findById(datosRegistroTopico.usuario_id()).get());
        topicoRepository.save(topico);

    }





}


