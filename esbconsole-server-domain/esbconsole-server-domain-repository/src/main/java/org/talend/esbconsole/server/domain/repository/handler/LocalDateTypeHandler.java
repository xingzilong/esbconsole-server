package org.talend.esbconsole.server.domain.repository.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * mybatis自定义 LocalDate 类型处理程序
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public class LocalDateTypeHandler implements TypeHandler<LocalDate> {

    @Override
    public void setParameter(PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setTimestamp(i, java.sql.Timestamp.valueOf(LocalDateTime.of(parameter, LocalTime.MIDNIGHT)));
        } else {
            ps.setNull(i, jdbcType.TYPE_CODE);
        }
    }

    @Override
    public LocalDate getResult(ResultSet rs, String columnName) throws SQLException {
        java.sql.Timestamp timestamp = rs.getTimestamp(columnName);
        return timestamp != null ? timestamp.toLocalDateTime().toLocalDate() : null;
    }

    @Override
    public LocalDate getResult(ResultSet rs, int columnIndex) throws SQLException {
        java.sql.Timestamp timestamp = rs.getTimestamp(columnIndex);
        return timestamp != null ? timestamp.toLocalDateTime().toLocalDate() : null;
    }

    @Override
    public LocalDate getResult(CallableStatement cs, int columnIndex) throws SQLException {
        java.sql.Timestamp timestamp = cs.getTimestamp(columnIndex);
        return timestamp != null ? timestamp.toLocalDateTime().toLocalDate() : null;
    }
}

