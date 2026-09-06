package com.github.mshourabi.client.tekegramagent.fiegn;

import com.github.mshourabi.client.tekegramagent.constants.ApiConstants;
import com.github.mshourabi.client.tekegramagent.dto.SyncMessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "${telegram-agent.service.name}",
        url = "${telegram-agent.service.url}",
        fallback = TelegramAgentClientFallback.class
)
public interface TelegramAgentClient {

    @PostMapping(ApiConstants.SEND_SYNC)
    ResponseEntity<SyncMessageDTO.SendDirectResponse> sendMessage(@RequestBody SyncMessageDTO.SendDirectRequest sendDirectRequest);
}
