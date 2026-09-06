package com.github.mshourabi.client.tekegramagent.fiegn;

import com.github.mshourabi.client.tekegramagent.dto.SyncMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TelegramAgentClientFallback implements TelegramAgentClient {

    private static final Logger log = LoggerFactory.getLogger(TelegramAgentClientFallback.class);

    @Override
    public ResponseEntity<SyncMessageDTO.SendDirectResponse> sendMessage(SyncMessageDTO.SendDirectRequest sendDirectRequest) {
        log.error("Fallback sendMessage in TelegramAgentFallback");
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .build();
    }
}
