package top.wenxyn.partner.manager.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/28 0:39
 */
public class EncryptUtil {

    public static String encode(String origin){
        return new BCryptPasswordEncoder().encode(origin);
    }

    public static boolean match(String origin, String password){
        return new BCryptPasswordEncoder().matches(origin, password);
    }
}
