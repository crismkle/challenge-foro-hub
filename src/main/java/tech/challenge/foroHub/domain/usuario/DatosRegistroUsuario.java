package tech.challenge.foroHub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(
        @NotBlank
        String nombre,
        @Email
        @NotNull
        String email,
        @NotBlank
        String clave,
        @NotBlank
        String perfil
) {

        public DatosRegistroUsuario(String nombre, String email, String clave, String perfil) {
                this.nombre = nombre;
                this.email = email;
                this.clave = clave;
                this.perfil = perfil;
        }
}
