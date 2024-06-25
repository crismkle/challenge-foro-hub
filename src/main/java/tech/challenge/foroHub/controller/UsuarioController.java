package tech.challenge.foroHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.challenge.foroHub.domain.usuario.UsuarioRepository;
import tech.challenge.foroHub.domain.usuario.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService service;


    @GetMapping
    public ResponseEntity<Page> listadoUsuarios(@PageableDefault(size = 10) Pageable paginacion){

        return service.listarUsuarios(paginacion);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id){

        return service.eliminarUsuario(id);
    }

}
