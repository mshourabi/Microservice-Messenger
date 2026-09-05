package com.github.mshourabi.client.telegramsender.fiegn;

import com.github.mshourabi.client.telegramsender.dto.SyncMessageDTO;
import com.github.mshourabi.client.telegramsender.constants.ApiConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "${telegram-sender.service.name}",
        url = "${telegram-sender.service.url}",
        fallback = TelegramSenderClientFallback.class
)
public interface TelegramSenderClient {

    @PostMapping(ApiConstants.SEND_SYNC)
    ResponseEntity<SyncMessageDTO.SendDirectResponse> sendMessage(@RequestBody SyncMessageDTO.SendDirectRequest sendDirectRequest);
}
