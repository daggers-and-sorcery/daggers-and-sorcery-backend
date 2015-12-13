package com.morethanheroic.swords.common.sql;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

/**
 * Map Java 8 LocalTime &lt;-&gt; java.sql.Time
 */
@MappedTypes(LocalTime.class)
public class LocalTimeHandler extends BaseTypeHandler<LocalTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalTime parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            ps.setTime(i, null);
        } else {
            ps.setTime(i, Time.valueOf(parameter));
        }
    }

    @Override
    public LocalTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Time time = rs.getTime(columnName);
        if (time != null) {
            return time.toLocalTime();
        }
        return null;
    }

    @Override
    public LocalTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Time time = rs.getTime(columnIndex);
        if (time != null) {
            return time.toLocalTime();
        }
        return null;
    }

    @Override
    public LocalTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Time time = cs.getTime(columnIndex);
        if (time != null) {
            return time.toLocalTime();
        }
        return null;
    }
}
