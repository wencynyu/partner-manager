package top.wenxyn.partner.manager.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/5 3:58
 */
@Aspect
@Slf4j
@Component
public class LogAspect {
    @Pointcut("execution(public * top.wenxyn.partner.manager.controller.*.*(..))")
    public void controllerPoint(){
    }

    @Around("controllerPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder stringBuilder = new StringBuilder();
        String className = joinPoint.getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        stringBuilder.append(className)
                .append(" ::")
                .append(methodName)
                .append(" start:: ");
        long begin = System.currentTimeMillis();
        Object res = joinPoint.proceed(args);
        long end = System.currentTimeMillis();
        long executeTime = end - begin;
        stringBuilder.append("the method cost ").append(executeTime).append("ms to execute :: ");
        if (executeTime < 50){
            stringBuilder.append("fast method");
            log.info(stringBuilder.toString());
        }else if (executeTime > 100){
            stringBuilder.append("slow method");
            log.warn(stringBuilder.toString());
        }else {
            stringBuilder.append("normal method");
            log.info(stringBuilder.toString());
        }
        return res;
    }
}
