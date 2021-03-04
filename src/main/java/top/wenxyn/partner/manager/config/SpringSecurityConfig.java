package top.wenxyn.partner.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.wenxyn.partner.manager.component.login.AuthenticationManagerProcessingFilter;
import top.wenxyn.partner.manager.service.auth.SystemUserDetailsService;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManagerProcessingFilter authenticationManagerProcessingFilter;

    @Autowired
    private SystemUserDetailsService systemUserDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 通过重载，配置user-detail服务
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(systemUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    private static final String[] WHITE_LIST = {"/register", "/login", "/index",
                                                "/getVerifyCode", "/getActiveUserCount"};

    // 通过重载，配置如何通过拦截器保护请求
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(WHITE_LIST).permitAll()
                // swagger start
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
                // other settings
                .antMatchers("/static/**").permitAll()
                .antMatchers("/**").authenticated()
                .and().rememberMe()
                .and().logout().logoutSuccessUrl("/index").permitAll()
                // 通过加载鉴权过滤器来进行自定义登录接口
                .and().addFilterAt(authenticationManagerProcessingFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().maximumSessions(1).expiredUrl("/index");
    }

    // 通过重载，配置Spring Security的Filter链
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
