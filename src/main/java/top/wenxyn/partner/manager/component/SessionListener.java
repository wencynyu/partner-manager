package top.wenxyn.partner.manager.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    private RedisTemplate<String, Integer> redisTemplate;

    @PostConstruct
    public void init(){
        redisTemplate.opsForValue().setIfAbsent(Constant.ACTIVE_USER_COUNT, 0);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        redisTemplate.opsForValue().increment(Constant.ACTIVE_USER_COUNT);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        redisTemplate.opsForValue().decrement(Constant.ACTIVE_USER_COUNT);
    }
}
