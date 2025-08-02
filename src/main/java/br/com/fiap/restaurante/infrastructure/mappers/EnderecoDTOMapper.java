package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.domain.entities.Endereco;
import br.com.fiap.restaurante.infrastructure.dtos.EnderecoDTO;

public class EnderecoDTOMapper {

    private EnderecoDTOMapper() {
    }

    public static Endereco toDomain(final EnderecoDTO enderecoDTO) {
        return new Endereco(
                null,
                enderecoDTO.getLogradouro(),
                enderecoDTO.getNumero(),
                enderecoDTO.getComplemento(),
                enderecoDTO.getBairro(),
                enderecoDTO.getCidade(),
                enderecoDTO.getEstado(),
                enderecoDTO.getCep()
        );
    }

    public static EnderecoDTO toDTO(final Endereco endereco) {
        return EnderecoDTO.builder()
                .logradouro(endereco.logradouro())
                .numero(endereco.numero())
                .complemento(endereco.complemento())
                .bairro(endereco.bairro())
                .cidade(endereco.cidade())
                .estado(endereco.estado())
                .cep(endereco.cep()).build();
    }
}
