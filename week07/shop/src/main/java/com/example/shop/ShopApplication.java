package com.example.shop;

import com.example.shop.config.init.ConfigurableApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.shop.mapper")
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ShopApplication.class);
        springApplication.addInitializers(new ConfigurableApplication());
        springApplication.run(args);

    }

}
