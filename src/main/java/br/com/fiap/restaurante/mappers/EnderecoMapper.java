package br.com.fiap.restaurante.mappers;

import br.com.fiap.restaurante.dtos.EnderecoDTO;
import br.com.fiap.restaurante.entities.EnderecoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class EnderecoMapper {


    public EnderecoDTO mapToEnderecoDTO(final EnderecoEntity enderecoEntity) {
        return EnderecoDTO.builder()
                .cep(enderecoEntity.getCep())
                .logradouro(enderecoEntity.getLogradouro())
                .numero(enderecoEntity.getNumero())
                .complemento(enderecoEntity.getComplemento())
                .bairro(enderecoEntity.getBairro())
                .cidade(enderecoEntity.getCidade())
                .estado(enderecoEntity.getEstado())
                .build();
    }

    public EnderecoEntity mapToEnderecoEntity(final EnderecoDTO enderecoDTO) {

        return EnderecoEntity.builder()
                .cep(enderecoDTO.getCep())
                .logradouro(enderecoDTO.getLogradouro())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .bairro(enderecoDTO.getBairro())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .build();
    }
}
