package com.github.mshourabi.distributor;

import com.github.mshourabi.client.telegramsender.fiegn.TelegramSenderClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.github.mshourabi.distributor",
                "com.github.mshourabi.client"
        }
)
@EnableFeignClients(clients = TelegramSenderClient.class)
public class DistributorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributorApplication.class, args);
    }

}
