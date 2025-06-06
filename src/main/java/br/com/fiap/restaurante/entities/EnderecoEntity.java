package br.com.fiap.restaurante.entities;

import br.com.fiap.restaurante.enums.UfEnum;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "endereco")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    @Enumerated(EnumType.STRING)
    private UfEnum estado;
    private String cep;
}
