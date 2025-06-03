package br.com.fiap.restaurante.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SenhasIguaisValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SenhasIguais {
    String message() default "A nova senha e a confirmação não conferem.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
