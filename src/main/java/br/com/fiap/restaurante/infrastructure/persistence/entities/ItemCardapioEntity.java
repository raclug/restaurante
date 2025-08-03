package br.com.fiap.restaurante.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "item_cardapio")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCardapioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String descricao;

    private Double preco;

    private Boolean disponivelApenasNoRestaurante;

    private String foto;
}
