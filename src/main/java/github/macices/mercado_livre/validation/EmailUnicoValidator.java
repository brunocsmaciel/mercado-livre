package github.macices.mercado_livre.validation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, String> {

    @Autowired
    EntityManager entityManager;

    @Override
    public void initialize(EmailUnico constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {

        Query consultaEmailUnico = entityManager.createQuery("select 1 from Usuario u where u.login = :email")
                .setParameter("email", login);
        List<?> resultaList = consultaEmailUnico.getResultList();

        return resultaList.isEmpty();
    }
}
