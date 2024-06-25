package tech.challenge.foroHub.domain.usuario.validaciones;

import tech.challenge.foroHub.domain.topico.DatosRegistroTopico;
import tech.challenge.foroHub.domain.usuario.DatosRegistroUsuario;
import tech.challenge.foroHub.domain.usuario.DatosRespuestaUsuario;

public interface ValidadorDeUsuarios {

    public void validar(DatosRegistroUsuario datosRegistroUsuario);
}
