package br.com.fiap.restaurante.entities;

import br.com.fiap.restaurante.enums.TipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

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

    private String login;

    private String senha;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime ultimaAlteracao;

    private String endereco;

    @Enumerated(EnumType.STRING)
    private TipoUsuarioEnum tipoUsuario;
}
