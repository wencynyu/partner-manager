package top.wenxyn.partner.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import top.wenxyn.partner.manager.service.auth.SystemUserDetailsService;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

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

    // 通过重载，配置如何通过拦截器保护请求
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("").permitAll()
                .antMatchers("").hasAuthority("")
                .and().formLogin()
                .loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
                .and().exceptionHandling().accessDeniedPage("/loginfail")
                .and().logout().permitAll();
    }

    // 通过重载，配置Spring Security的Filter链
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
