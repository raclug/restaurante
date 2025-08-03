package br.com.fiap.restaurante.infrastructure.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
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
@Schema(description = "DTO que representa um restaurante do sistema")
public class RestauranteDTO {

    @JsonProperty(access = READ_ONLY)
    @Schema(description = "Identificador do restaurante", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Schema(description = "Nome do restaurante", example = "McDowell's")
    private String nome;

    @NotNull(message = "O endereço é obrigatório.")
    @Valid
    @Schema(description = "Endereço do restaurante")
    private EnderecoDTO endereco;

    @NotBlank(message = "O tipo de cozinha é obrigatório.")
    @Schema(description = "Tipo de cozinha do restaurante", example = "Fast Food")
    private String tipoCozinha;

    @NotBlank(message = "O horário de abertura é obrigatório.")
    @Schema(description = "Horário de abertura do restaurante", example = "09:00")
    private String horarioAbertura;

    @NotBlank(message = "O horário de fechamento é obrigatório.")
    @Schema(description = "Horário de fechamento do restaurante", example = "22:00")
    private String horarioFechamento;

    @NotNull(message = "O responsável pelo restaurante é obrigatório.")
    @Schema(description = "Identificador do responsável pelo restaurante")
    private Long responsavelId;
}
