package com.demo.config;



import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author YanZhao
 * @description
 * @date 2022年09月18日 21:25
 */



@Configuration
@EnableSwagger2
@EnableSwagger2Doc
public class SwaggerConfig {

    private final static String APP_PAGE = "com.demo.controller.app";
    private final static String SCHEDULE_PAGE = "com.demo.controller.schedule";

    @Bean
    public Docket getAppRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo("晓风碎月---用户接口API文档")).groupName("APP")
                .select()
                .apis(RequestHandlerSelectors.basePackage(APP_PAGE))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket getScheduleRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo("晓风碎月---后端接口API文档")).groupName("SCHEDULE")
                .select()
                .apis(RequestHandlerSelectors.basePackage(SCHEDULE_PAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo(String title) {
        Contact contact = new Contact("晓风碎月", "http://localhost:8080", "1770285990@qq.com");
        return new ApiInfoBuilder()
                .title(title)
                .description("平台管理服务api")
                .contact(contact)
                .build();
    }

}
