package tech.challenge.foroHub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(
        @NotBlank
        String nombre,
        @Email
        String email,
        @NotBlank
        String clave,
        @NotNull
        Perfil perfil
) {
        public DatosRegistroUsuario(String nombre, String email, String clave, Perfil perfil) {
                this.nombre = nombre;
                this.email = email;
                this.clave = clave;
                this.perfil = perfil;
        }
}
