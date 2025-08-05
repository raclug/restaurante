package br.com.fiap.restaurante.infrastructure.persistence.entities;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "restaurante")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoEntity endereco;

    private String tipoCozinha;

    private String horarioAbertura;

    private String horarioFechamento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity responsavel;
}
