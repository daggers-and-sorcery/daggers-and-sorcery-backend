package com.swordssorcery.server.validator;

import com.swordssorcery.server.validator.annotation.UniqueInDb;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueInDbValidator implements ConstraintValidator<UniqueInDb, String> {

    private UniqueInDb uniqueInDb;

    @Autowired
    private EntityManagerFactory factory;

    @Override
    public void initialize(UniqueInDb uniqueInDb) {
        this.uniqueInDb = uniqueInDb;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cxt) {
        Criteria criteria = factory.unwrap(SessionFactory.class).openSession().createCriteria(uniqueInDb.model());
        criteria.add(Restrictions.eq(uniqueInDb.field(), value));

        return criteria.list().size() == 0;
    }

}