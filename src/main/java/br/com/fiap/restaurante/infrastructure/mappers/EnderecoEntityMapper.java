package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.domain.entities.Endereco;
import br.com.fiap.restaurante.infrastructure.persistence.entities.EnderecoEntity;

public class EnderecoEntityMapper {

    private EnderecoEntityMapper(){}

    public static EnderecoEntity toEntity(final Endereco endereco) {

        return EnderecoEntity.builder()
                .id(endereco.id())
                .logradouro(endereco.logradouro())
                .numero(endereco.numero())
                .complemento(endereco.complemento())
                .bairro(endereco.bairro())
                .cidade(endereco.cidade())
                .estado(endereco.estado())
                .cep(endereco.cep())
                .build();
    }

    public static Endereco toDomain(final EnderecoEntity enderecoEntity) {

        return new Endereco(
                enderecoEntity.getId(),
                enderecoEntity.getLogradouro(),
                enderecoEntity.getNumero(),
                enderecoEntity.getComplemento(),
                enderecoEntity.getBairro(),
                enderecoEntity.getCidade(),
                enderecoEntity.getEstado(),
                enderecoEntity.getCep()
        );
    }
}
