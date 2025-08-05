package br.com.fiap.restaurante.domain.entities;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemCardapio {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Boolean disponivelApenasNoRestaurante;
    private String foto;
}
