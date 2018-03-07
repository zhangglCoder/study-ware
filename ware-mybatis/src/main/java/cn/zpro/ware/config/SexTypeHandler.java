package cn.zpro.ware.config;

import cn.zpro.ware.entity.SexEum;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhanggl
 */
public class SexTypeHandler extends EnumOrdinalTypeHandler {
    public SexTypeHandler(Class type) {
        super(type);
    }

    @Override
    public Enum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return SexEum.findSexEum(rs.getShort(columnName));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Enum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, ((SexEum)parameter).getValue());
    }
}
