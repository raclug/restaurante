package br.com.fiap.restaurante.infrastructure.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginaDTO {

    private Integer pagina;

    private Integer tamanhoPagina;
}
