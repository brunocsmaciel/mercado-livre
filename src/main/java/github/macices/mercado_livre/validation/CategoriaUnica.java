package github.macices.mercado_livre.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target(FIELD)
@Retention(RUNTIME)
@NotBlank
@Constraint(validatedBy = CategoriaValidator.class)
public @interface CategoriaUnica {

    String message() default "Categoria já cadastrada";

    Class<?>[] groups() default { };

    Class <? extends Payload>[] payload() default { };
}
