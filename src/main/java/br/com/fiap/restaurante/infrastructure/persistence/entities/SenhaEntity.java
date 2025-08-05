package br.com.fiap.restaurante.infrastructure.persistence.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(name = "senha")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SenhaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String senha;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime ultimaAlteracao;

    @OneToOne(mappedBy = "senha", cascade = CascadeType.ALL)
    private UsuarioEntity usuario;

}
