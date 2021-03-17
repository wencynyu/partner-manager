package top.wenxyn.partner.manager.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.wenxyn.partner.manager.service.auth.SystemUser;

import java.security.Principal;
import java.util.UUID;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/28 0:39
 */
public final class AuthUtil {

    public static String encode(String origin){
        return new BCryptPasswordEncoder().encode(origin);
    }

    public static boolean match(String origin, String password){
        return new BCryptPasswordEncoder().matches(origin, password);
    }

    public static SystemUser getCurUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (SystemUser) authentication.getPrincipal();
    }

    public static String getUUIDNo(){
        return UUID.randomUUID().toString();
    }
}
