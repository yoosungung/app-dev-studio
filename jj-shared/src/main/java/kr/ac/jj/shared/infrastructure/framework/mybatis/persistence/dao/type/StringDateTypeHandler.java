package kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;

@MappedTypes(value = { String.class })
@MappedJdbcTypes(value = { JdbcType.DATE })
public class StringDateTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Date date;

        try {
            date = DateUtils.parseDate(parameter, new String[] { "yyyyMMdd" });
        } catch (ParseException e) {
            throw new BaseException(e);
        }

        ps.setDate(i, new java.sql.Date(date.getTime()));
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Date date = rs.getDate(columnName);

        if (date != null) {
            return DateFormatUtils.format(date, "yyyyMMdd");
        }

        return null;
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Date date = rs.getDate(columnIndex);

        if (date != null) {
            return DateFormatUtils.format(date, "yyyyMMdd");
        }

        return null;
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Date date = cs.getDate(columnIndex);

        if (date != null) {
            return DateFormatUtils.format(date, "yyyyMMdd");
        }

        return null;
    }

}
