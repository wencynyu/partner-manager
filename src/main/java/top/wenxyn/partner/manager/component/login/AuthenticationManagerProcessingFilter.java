package top.wenxyn.partner.manager.component.login;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import top.wenxyn.partner.manager.common.Constant;
import top.wenxyn.partner.manager.entity.TAuthUser;
import top.wenxyn.partner.manager.entity.vo.LoginUserVO;
import top.wenxyn.partner.manager.util.RequestUtil;
import top.wenxyn.partner.manager.util.SerializeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/5 1:07
 */
@Component
public class AuthenticationManagerProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public AuthenticationManagerProcessingFilter(LoginAuthenticationManager loginAuthenticationManager,
                                                 LoginSuccessHandler loginSuccessHandler,
                                                 LoginFailHandler loginFailHandler) {
        super(new AntPathRequestMatcher("/login", "POST"));
        this.setAuthenticationManager(loginAuthenticationManager);
        this.setAuthenticationSuccessHandler(loginSuccessHandler);
        this.setAuthenticationFailureHandler(loginFailHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        if (request.getContentType() == null || !request.getContentType().contains("application/json")) {
            throw new AuthenticationServiceException("request content type error :: not json request body "
                    + request.getContentType());
        }

        UsernamePasswordAuthenticationToken authRequest;
        try {

            String requestBody = RequestUtil.getRequestBody(request);
            // 将前端传递的数据转换成jsonBean数据格式
            LoginUserVO authUser = Optional.ofNullable(SerializeUtil.string2Obj(requestBody, LoginUserVO.class))
                    .orElse(new LoginUserVO());
            if (StringUtils.isEmpty(authUser.getUsername())
                    || StringUtils.isEmpty(authUser.getPassword())
                    || StringUtils.isEmpty(authUser.getVerifyCode())){
                throw new AuthenticationServiceException("request error :: lack of request param " +
                        ":: login need username, password and verify code params");
            }
            String verifyCodeInSession = (String) request.getSession().getAttribute(Constant.VERIFY_CODE_SESSION_NAME);
            if (!authUser.getVerifyCode().equalsIgnoreCase(verifyCodeInSession)){
                throw new AuthenticationServiceException("request error :: verify code error");
            }
            authRequest = new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword(), null);
            authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
