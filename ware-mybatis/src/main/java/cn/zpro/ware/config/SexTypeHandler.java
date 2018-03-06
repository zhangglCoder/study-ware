package cn.zpro.ware.config;

import cn.zpro.ware.entity.SexEum;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhanggl
 */
@MappedJdbcTypes(JdbcType.TINYINT)
public class SexTypeHandler extends EnumOrdinalTypeHandler {
    public SexTypeHandler(Class type) {
        super(type);
    }

    @Override
    public Enum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        short aShort = rs.getShort(columnName);

        return SexEum.findSexEum(aShort);
    }
}
