package com.github.mshourabi.telegramsender.fiegn;

import com.github.mshourabi.telegramsender.config.ApiConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("${spring.application.name}")
public interface MessageClient {

    @PostMapping(ApiConstants.MESSAGES)
    ResponseEntity<MessageDTO.SendResponse> createMessage(@RequestBody MessageDTO.SendRequest sendRequest);
}
