package kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(value = { Locale.class })
@MappedJdbcTypes(value = { JdbcType.VARCHAR })
public class LocaleStringTypeHandler extends BaseTypeHandler<Locale> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Locale parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter.toString());
    }

    @Override
    public Locale getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String localeString = rs.getString(columnName);

        if (localeString != null) {
            return LocaleUtils.toLocale(localeString);
        }

        return null;
    }

    @Override
    public Locale getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String localeString = rs.getString(columnIndex);

        if (localeString != null) {
            return LocaleUtils.toLocale(localeString);
        }

        return null;
    }

    @Override
    public Locale getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String localeString = cs.getString(columnIndex);

        if (localeString != null) {
            return LocaleUtils.toLocale(localeString);
        }

        return null;
    }

}
