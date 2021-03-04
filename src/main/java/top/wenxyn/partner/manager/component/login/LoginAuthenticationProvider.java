package top.wenxyn.partner.manager.component.login;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import top.wenxyn.partner.manager.common.Constant;
import top.wenxyn.partner.manager.dao.TUserRepository;
import top.wenxyn.partner.manager.entity.TAuthUser;
import top.wenxyn.partner.manager.service.auth.SystemUserDetailsService;
import top.wenxyn.partner.manager.util.EncryptUtil;
import top.wenxyn.partner.manager.util.ResponseUtil;
import top.wenxyn.partner.manager.util.SerializeUtil;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/5 1:17
 */
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private SystemUserDetailsService systemUserDetailsService;

    @Autowired
    private TUserRepository tUserRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String USER_DETAILS_SUFFIX = Constant.SEPARATE_COMMA + Constant.USER_DETAILS_SUFFIX;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        if (StringUtils.isEmpty(username) ||  StringUtils.isEmpty(password)){
            throw new BadCredentialsException("username or password is null");
        }

        UserDetails userDetails = systemUserDetailsService.loadUserByUsername(username);
        if (!EncryptUtil.match(password, userDetails.getPassword())) {
            throw new BadCredentialsException("username or password incorrect");
        }

        // login success, try log times set to 0
        TAuthUser loginUser = tUserRepository.findByUsername(username);
        loginUser.setLoginTryTimes(0);
        tUserRepository.save(loginUser);
        stringRedisTemplate.opsForValue().set(username + USER_DETAILS_SUFFIX, SerializeUtil.obj2String(loginUser));

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
