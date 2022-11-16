package kr.ac.jj.shared.infrastructure.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.type.JdbcType;

import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.type.LongDateTypeHandler;

public class LongDateAddTypeHandler extends LongDateTypeHandler {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType)
            throws SQLException {
        Date date = DateUtils.addDays(new Date(parameter), 1);

        ps.setDate(i, new java.sql.Date(date.getTime()));
    }

}
