package github.macices.mercado_livre.validation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Long> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistsId params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) {
            return true;
        }
        Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
        query.setParameter("value", value);


        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <= 1, "aconteceu algo bizarro e você tem mais de um " + klass + " com o atributo " + domainAttribute + " com o valor = " + value);

        return !list.isEmpty();
    }
}
