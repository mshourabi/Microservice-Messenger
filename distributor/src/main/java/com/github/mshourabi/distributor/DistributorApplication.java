package com.github.mshourabi.distributor;

import com.github.mshourabi.client.tekegramagent.fiegn.TelegramAgentClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(
        scanBasePackages = {
                "com.github.mshourabi.distributor",
                "com.github.mshourabi.client"
        }
)
@EnableJpaAuditing
@EnableFeignClients(clients = TelegramAgentClient.class)
public class DistributorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributorApplication.class, args);
    }

}
