package cn.zpro.ware.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

@Intercepts({
        @Signature(type = Executor.class, method = "update",
                args = {MappedStatement.class, Object.class})
})
public class MyPlugin implements Interceptor {



    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        invocation.getArgs()[0] = getCheckAndResetSQL(invocation);
        return invocation.proceed();
    }

    private Object getCheckAndResetSQL(Invocation invocation) {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object parameter = args[1];
        mappedStatement.getSqlSource().getBoundSql(parameter);
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        String resetSql = doCheckAndResetSQL(boundSql.getSql());
        ///
        return getMappedStatement(mappedStatement, boundSql, resetSql);
    }

    private String doCheckAndResetSQL(String sql) {

        sql = "UPDATE mmall_category set name = '家用电器999',sort_order = 9 where id = '100001';";
        return sql;
    }
    private Object getMappedStatement(MappedStatement mappedStatement,
                                      BoundSql boundSql, String resetSql) {
        final BoundSql newBoundSql = new BoundSql(
                mappedStatement.getConfiguration(), resetSql,
                boundSql.getParameterMappings(), boundSql.getParameterObject());


        MappedStatement.Builder builder = new MappedStatement.Builder(
                mappedStatement.getConfiguration(), mappedStatement.getId(),
                new SqlSource() {
                    public BoundSql getBoundSql(Object parameterObject) {
                        return newBoundSql;
                    }
                }, mappedStatement.getSqlCommandType());
        builder.cache(mappedStatement.getCache());
        builder.fetchSize(mappedStatement.getFetchSize());
        builder.flushCacheRequired(mappedStatement.isFlushCacheRequired());
        builder.keyGenerator(mappedStatement.getKeyGenerator());
        //builder.keyProperty(mappedStatement.getKeyProperty());
        builder.resource(mappedStatement.getResource());
        builder.resultMaps(mappedStatement.getResultMaps());
        builder.resultSetType(mappedStatement.getResultSetType());
        builder.statementType(mappedStatement.getStatementType());
        builder.timeout(mappedStatement.getTimeout());
        builder.useCache(mappedStatement.isUseCache());
        return builder.build();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
