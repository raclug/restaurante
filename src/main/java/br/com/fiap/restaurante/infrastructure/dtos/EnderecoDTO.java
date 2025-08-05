package br.com.fiap.restaurante.infrastructure.dtos;

import br.com.fiap.restaurante.domain.entities.UfEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO que representa o endereço do usuário")
public class EnderecoDTO {

    @NotNull(message = "O logradouro é obrigatório.")
    @Schema(description = "Logradouro do endereço", example = "Rua das Flores")
    private String logradouro;

    @NotNull(message = "O número é obrigatório.")
    @Schema(description = "Número do endereço", example = "123")
    private String numero;

    @Schema(description = "Complemento do endereço", example = "Apto 45")
    private String complemento;

    @NotNull(message = "O bairro é obrigatório.")
    @Schema(description = "Bairro do endereço", example = "Centro")
    private String bairro;

    @NotNull(message = "A cidade é obrigatória.")
    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String cidade;

    @NotNull(message = "O estado é obrigatório.")
    @Schema(description = "Estado do endereço", example = "SP")
    private UfEnum estado;

    @NotNull(message = "O CEP é obrigatório.")
    @Schema(description = "CEP do endereço", example = "01001-000")
    private String cep;
}
