package top.wenxyn.partner.manager.component.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/5 1:13
 */
@Component
public class LoginAuthenticationManager implements AuthenticationManager {

    @Autowired
    private LoginAuthenticationProvider loginAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication result = loginAuthenticationProvider.authenticate(authentication);
        if (Objects.nonNull(result)) {
            return result;
        }
        throw new ProviderNotFoundException("Authentication failed!");
    }
}
