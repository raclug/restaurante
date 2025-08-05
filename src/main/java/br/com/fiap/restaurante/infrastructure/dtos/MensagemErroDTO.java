package br.com.fiap.restaurante.infrastructure.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO que representa uma mensagem de erro retornada pela API")
public class MensagemErroDTO {

    @Schema(description = "Código de status HTTP do erro", example = "400")
    private Integer status;

    @Schema(description = "Lista de mensagens de erro detalhadas", example = "[\"O campo nome é obrigatório.\", \"E-mail inválido.\"]")
    private List<String> erros;
}
