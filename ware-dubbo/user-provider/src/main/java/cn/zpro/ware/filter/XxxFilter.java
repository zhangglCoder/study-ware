package cn.zpro.ware.filter;

import com.alibaba.dubbo.rpc.*;

/**
 * 自定义拦截基于SPI
 */
public class XxxFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // before filter ...
        System.out.println("测试拦截 XxxFilter-start ");
        Result result = invoker.invoke(invocation);
        // after filter ...
        System.out.println("测试拦截 XxxFilter-end");
        return result;
    }
}
