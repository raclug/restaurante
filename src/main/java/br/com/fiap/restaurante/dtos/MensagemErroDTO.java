package br.com.fiap.restaurante.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MensagemErroDTO {

    private Integer status;

    private String mensagem;
}
