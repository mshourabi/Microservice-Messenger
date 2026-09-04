package com.github.mshourabi.client.telegramsender.fiegn;

import com.github.mshourabi.client.telegramsender.dto.MessageDTO;
import com.github.mshourabi.client.telegramsender.constants.ApiConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("${subsystem.telegram-sender.name}")
public interface MessageClient {

    @PostMapping(ApiConstants.MESSAGES)
    ResponseEntity<MessageDTO.SendDirectResponse> createMessage(@RequestBody MessageDTO.SendDirectRequest sendDirectRequest);
}
