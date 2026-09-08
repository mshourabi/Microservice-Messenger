package com.github.mshourabi.telegramagent;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TelegramAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramAgentApplication.class, args);
    }


    /**
     * 
     * @return
     */
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
