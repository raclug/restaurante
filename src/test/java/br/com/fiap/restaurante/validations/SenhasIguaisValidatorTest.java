package br.com.fiap.restaurante.validations;

import br.com.fiap.restaurante.infrastructure.dtos.SenhaDTO;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SenhasIguaisValidatorTest {
    private final SenhasIguaisValidator validator = new SenhasIguaisValidator();
    private final ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

    @Test
    void deveRetornarTrueQuandoSenhasForemIguais() {
        SenhaDTO dto = new SenhaDTO();
        dto.setNovaSenha("abc123");
        dto.setConfirmacaoSenha("abc123");

        assertTrue(validator.isValid(dto, context));
    }

    @Test
    void deveRetornarFalseQuandoSenhasForemDiferentes() {
        SenhaDTO dto = new SenhaDTO();
        dto.setNovaSenha("abc123");
        dto.setConfirmacaoSenha("def456");

        assertFalse(validator.isValid(dto, context));
    }

    @Test
    void deveRetornarTrueQuandoDtoForNulo() {
        assertTrue(validator.isValid(null, context));
    }

    @Test
    void deveRetornarTrueQuandoNovaSenhaForNula() {
        SenhaDTO dto = new SenhaDTO();
        dto.setNovaSenha(null);
        dto.setConfirmacaoSenha("abc123");

        assertTrue(validator.isValid(dto, context));
    }

    @Test
    void deveRetornarTrueQuandoConfirmacaoSenhaForNula() {
        SenhaDTO dto = new SenhaDTO();
        dto.setNovaSenha("abc123");
        dto.setConfirmacaoSenha(null);

        assertTrue(validator.isValid(dto, context));
    }
}