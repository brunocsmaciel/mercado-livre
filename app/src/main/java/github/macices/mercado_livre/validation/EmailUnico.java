package github.macices.mercado_livre.validation;


import github.macices.mercado_livre.validation.EmailUnicoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target(FIELD)
@Retention(RUNTIME)
@Email
@Constraint(validatedBy = EmailUnicoValidator.class)
public @interface EmailUnico {

    String message() default "Email já cadastrado";

    Class<?>[] groups() default { };

    Class <? extends Payload>[] payload() default { };
}
