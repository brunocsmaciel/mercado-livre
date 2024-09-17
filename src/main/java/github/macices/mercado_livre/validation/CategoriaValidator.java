package github.macices.mercado_livre.validation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoriaValidator implements ConstraintValidator<CategoriaUnica, String> {

    @Autowired
    private EntityManager manager;

    @Override
    public void initialize(CategoriaUnica constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String nomeCategoria, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("select 1 from Categoria c where c.nome = :nome")
                .setParameter("nome", nomeCategoria);

        List<?> resultList = query.getResultList();

        return resultList.isEmpty();
    }
}
