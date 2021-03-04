package top.wenxyn.partner.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/6 22:58
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Partner Manage System Document",
                "blog website: https://www.wenxyn.top welcome to visit.",
                "API V1.0",
                "https://www.google.com",
                new Contact("WenxinYu", "https://www.wenxyn.top", "yuwenxin980214@gmail.com"),
                "Apache", "http://www.apache.org/", Collections.emptyList());
    }
}
