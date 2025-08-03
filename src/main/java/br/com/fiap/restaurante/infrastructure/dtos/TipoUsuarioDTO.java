package br.com.fiap.restaurante.infrastructure.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO que representa um tipo de usuário do sistema")
public class TipoUsuarioDTO {

    @JsonProperty(access = READ_ONLY)
    @Schema(description = "Identificador do tipo de usuário", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Schema(description = "Nome do tipo de usuário", example = "Dono do Restaurante")
    private String nome;
}
