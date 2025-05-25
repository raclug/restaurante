package br.com.fiap.restaurante.validations;

import br.com.fiap.restaurante.dtos.SenhaDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SenhasIguaisValidator implements ConstraintValidator<SenhasIguais, SenhaDTO> {

    @Override
    public boolean isValid(SenhaDTO dto, ConstraintValidatorContext context) {

        if (dto == null || dto.getNovaSenha() == null || dto.getConfirmacaoSenha() == null) {
            return true;
        }

        return dto.getNovaSenha().equals(dto.getConfirmacaoSenha());
    }
}
