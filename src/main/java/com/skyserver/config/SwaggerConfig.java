package com.skyserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置文件
 */

@Configuration
@EnableSwagger2//开启swagger
public class SwaggerConfig {


    @Bean
    public Docket docket(){

        //设置请求头参数
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)//配置swagger的开启
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rein"))
                //.path():过滤的路径 相当于第二层扫描
                .build()
                .globalOperationParameters(pars);
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("苍穹外卖--api文档")
                .description("苍穹外卖接口描述")
                .version("1.0")
                .contact(new Contact("rein","http://baidu.com","843097542@qq.com"))
                .build();
    }


}