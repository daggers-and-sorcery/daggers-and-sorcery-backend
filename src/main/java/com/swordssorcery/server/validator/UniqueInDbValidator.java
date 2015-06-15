package com.swordssorcery.server.validator;

import com.swordssorcery.server.validator.annotation.UniqueInDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueInDbValidator implements ConstraintValidator<UniqueInDb, String> {

    @Autowired
    private MongoOperations mongoOperation;

    private UniqueInDb uniqueInDb;

    @Override
    public void initialize(UniqueInDb uniqueInDb) {
        this.uniqueInDb = uniqueInDb;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cxt) {
        Query query2 = new Query();
        query2.addCriteria(Criteria.where(uniqueInDb.field()).is(value));

        System.out.println("value = " + value);
        System.out.println("exists = " + mongoOperation.exists(query2, uniqueInDb.model()));

        return !mongoOperation.exists(query2, uniqueInDb.model());
    }

}