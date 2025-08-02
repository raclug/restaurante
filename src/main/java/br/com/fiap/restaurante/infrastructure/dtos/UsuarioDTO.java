package br.com.fiap.restaurante.infrastructure.dtos;

import br.com.fiap.restaurante.domain.entities.TipoUsuarioEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO que representa um usuário do sistema")
public class UsuarioDTO {

    @JsonProperty(access = READ_ONLY)
    @Schema(description = "Identificador do usuário", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Schema(description = "Nome do usuário", example = "João da Silva")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail é inválido.")
    @Schema(description = "E-mail do usuário", example = "joao@email.com")
    private String email;

    @NotBlank(message = "O login é obrigatório.")
    @Schema(description = "Login único do usuário", example = "joaosilva")
    private String login;

    @NotBlank(message = "A senha é obrigatória.")
    @JsonProperty(access = WRITE_ONLY)
    @Schema(description = "Senha do usuário", example = "senhaForte123", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String senha;

    @NotNull(message = "O endereço é obrigatório.")
    @Valid
    @Schema(description = "Endereço do usuário")
    private EnderecoDTO endereco;

    @NotNull(message = "O tipo de usuário é obrigatório.")
    @Schema(description = "Tipo do usuário", example = "CLIENTE")
    private TipoUsuarioEnum tipoUsuario;
}
