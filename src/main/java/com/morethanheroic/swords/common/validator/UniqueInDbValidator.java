package com.morethanheroic.swords.common.validator;

import com.morethanheroic.swords.common.validator.annotation.UniqueInDb;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UniqueInDbValidator implements ConstraintValidator<UniqueInDb, String> {

    private UniqueInDb uniqueInDb;

    @Autowired
    private DataSource dataSource;

    @Override
    public void initialize(UniqueInDb uniqueInDb) {
        this.uniqueInDb = uniqueInDb;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cxt) {
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement("SELECT * FROM " + uniqueInDb.model() + " WHERE " + uniqueInDb.field() + " = ?");
            statement.setString(1, value);

            statement.execute();

            return !statement.getResultSet().first();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}