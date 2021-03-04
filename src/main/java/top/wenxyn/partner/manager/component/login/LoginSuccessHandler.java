package top.wenxyn.partner.manager.component.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import top.wenxyn.partner.manager.common.Constant;
import top.wenxyn.partner.manager.service.UserService;
import top.wenxyn.partner.manager.util.SerializeUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/5 1:13
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletRequest.getSession().removeAttribute(Constant.VERIFY_CODE_SESSION_NAME);
        PrintWriter writer = httpServletResponse.getWriter();
        writer.println(SerializeUtil.obj2String(authentication));
    }
}
