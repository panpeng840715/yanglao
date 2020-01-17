package com.kxt.yanglao;

import com.kxt.yanglao.watch.jpa.util.HttpClientService;
import org.apache.http.NameValuePair;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class YanglaoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(YanglaoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

}
