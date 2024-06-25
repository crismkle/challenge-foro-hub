package tech.challenge.foroHub.domain.usuario.validaciones;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import tech.challenge.foroHub.domain.usuario.DatosRegistroUsuario;
import tech.challenge.foroHub.domain.usuario.Perfil;

@Component
public class SoloPerfilEstudiante implements ValidadorDeUsuarios {

    @Override
    public void validar(DatosRegistroUsuario datosRegistroUsuario) {

        if (!datosRegistroUsuario.perfil().equalsIgnoreCase(String.valueOf(Perfil.ESTUDIANTE))){
            throw new ValidationException("Sólo se puede registrar como Estudiante");
        }
    }
}
