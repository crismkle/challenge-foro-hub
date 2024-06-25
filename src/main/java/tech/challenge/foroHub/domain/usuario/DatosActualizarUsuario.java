package tech.challenge.foroHub.domain.usuario;


public record DatosActualizarUsuario(
        String nombre,
        String email,
        String clave,
        String perfil
) {
}
