package br.com.fiap.restaurante.infrastructure.persistence.entities;

import br.com.fiap.restaurante.enums.TipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(name = "usuario")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String email;

    @Column(unique = true)
    private String login;

    @OneToOne(cascade = CascadeType.ALL)
    private SenhaEntity senha;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime ultimaAlteracao;

    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoEntity endereco;

    @Enumerated(EnumType.STRING)
    private TipoUsuarioEnum tipoUsuario;
}
