package tech.challenge.foroHub.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.challenge.foroHub.infra.errores.ValidacionDeIntegridad;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<Page> listarUsuarios(Pageable paginacion) {

        return ResponseEntity.ok(usuarioRepository.listarUsuarios(paginacion)
                .map(DatosRespuestaUsuario::new));
    }

    public ResponseEntity eliminarUsuario(Long id) {

        if (usuarioRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El usuario no fue encontrado. Verifique el id.");
        }

        usuarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
