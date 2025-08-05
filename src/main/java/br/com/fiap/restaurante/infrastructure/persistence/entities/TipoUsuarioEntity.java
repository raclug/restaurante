package br.com.fiap.restaurante.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tipo_usuario")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
}
