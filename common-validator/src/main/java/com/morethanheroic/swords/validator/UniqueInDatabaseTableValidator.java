package com.morethanheroic.swords.validator;

import com.morethanheroic.swords.validator.annotation.UniqueInDatabaseTable;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Validation logic for {@link UniqueInDatabaseTable}.
 */
public class UniqueInDatabaseTableValidator implements ConstraintValidator<UniqueInDatabaseTable, String> {

    @Autowired
    private DataSource dataSource;

    private String query;

    @Override
    public void initialize(UniqueInDatabaseTable uniqueInDatabaseTable) {
        query = "SELECT * FROM " + uniqueInDatabaseTable.table() + " WHERE " + uniqueInDatabaseTable.field() + " = ?";
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cxt) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, value);

            statement.execute();

            return !statement.getResultSet().first();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
