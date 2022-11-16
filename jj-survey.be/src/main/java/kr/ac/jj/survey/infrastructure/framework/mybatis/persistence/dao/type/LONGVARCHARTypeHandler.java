package kr.ac.jj.survey.infrastructure.framework.mybatis.persistence.dao.type;

import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

@MappedTypes(value = { String.class })
@MappedJdbcTypes(value = { JdbcType.LONGVARCHAR, JdbcType.LONGNVARCHAR })
public class LONGVARCHARTypeHandler implements TypeHandler<Object> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        String s = (String) parameter;
        StringReader reader = new StringReader(s);
        ps.setCharacterStream(i, reader, s.length());
    }

    @Override
    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName);
    }

    @Override
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex);
    }

    @Override
    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getString(columnIndex);
    }
}
