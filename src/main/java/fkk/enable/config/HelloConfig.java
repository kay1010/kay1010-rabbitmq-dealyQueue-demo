package fkk.enable.config;

import fkk.enable.Hello;
import org.springframework.context.annotation.Bean;

public class HelloConfig {
    @Bean
    public Hello hello(){
        return new Hello();
    }
}
