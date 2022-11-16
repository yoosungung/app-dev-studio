package kr.ac.jj.shared.infrastructure.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.type.JdbcType;

import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.type.StringDateTypeHandler;

public class StringDateAddTypeHandler extends StringDateTypeHandler {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Date date = DateUtils.addDays(this.parseDate(parameter), 1);

        ps.setDate(i, new java.sql.Date(date.getTime()));
    }

}
