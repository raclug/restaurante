package br.com.fiap.restaurante.infrastructure.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO que representa um item do cardápio")
public class ItemCardapioDTO {

    @JsonProperty(access = READ_ONLY)
    @Schema(description = "Identificador do item do cardápio", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Schema(description = "Nome do item do cardápio", example = "Hambúrguer Clássico")
    private String nome;

    @NotBlank(message = "A descrição é obrigatório.")
    @Schema(description = "Descrição do item do cardápio", example = "Um delicioso hambúrguer com queijo, alface e tomate")
    private String descricao;

    @NotNull(message = "O preço é obrigatório.")
    @Schema(description = "Preço do item do cardápio", example = "19.99")
    private Double preco;

    @NotNull(message = "A disponibilidade é obrigatória.")
    @Schema(description = "Indica se o item está disponível apenas no restaurante", example = "true")
    private Boolean disponivelApenasNoRestaurante;

    @NotNull(message = "O foto é obrigatória.")
    @Schema(description = "URL da foto do item do cardápio", example = "https://example.com/foto.jpg")
        private String foto;
}
