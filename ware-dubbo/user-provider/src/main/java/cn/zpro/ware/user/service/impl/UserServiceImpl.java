package cn.zpro.ware.user.service.impl;

import cn.zpro.ware.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author zhanggl
 */
@Service
public class UserServiceImpl implements UserService {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String hello() {
        return "world";
    }
}
