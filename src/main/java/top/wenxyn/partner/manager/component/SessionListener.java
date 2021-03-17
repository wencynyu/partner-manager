package top.wenxyn.partner.manager.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import top.wenxyn.partner.manager.common.Constant;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/5 1:12
 */
@Component
public class SessionListener implements HttpSessionListener {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostConstruct
    public void init(){
        stringRedisTemplate.opsForValue().setIfAbsent(Constant.ACTIVE_USER_COUNT, String.valueOf(0));
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        stringRedisTemplate.opsForValue().increment(Constant.ACTIVE_USER_COUNT);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        stringRedisTemplate.opsForValue().decrement(Constant.ACTIVE_USER_COUNT);
    }
}
