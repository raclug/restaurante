package br.com.fiap.restaurante.dtos;

import br.com.fiap.restaurante.enums.TipoUsuarioEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para alteração de dados do usuário")
public class AlteracaoUsuarioDTO {

    @JsonProperty(value = "id", access = READ_ONLY)
    @Schema(description = "Identificador do usuário", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Schema(description = "Nome do usuário", example = "João da Silva")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail inválido.")
    @Schema(description = "E-mail do usuário", example = "joao@email.com")
    private String email;

    @NotNull(message = "O endereço é obrigatório.")
    @Valid
    @Schema(description = "Endereço do usuário")
    private EnderecoDTO endereco;

    @NotNull(message = "O tipo de usuário é obrigatório.")
    @Schema(description = "Tipo do usuário", example = "CLIENTE")
    private TipoUsuarioEnum tipoUsuario;
}
