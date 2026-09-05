package com.github.mshourabi.client.telegramsender.fiegn;

import com.github.mshourabi.client.telegramsender.dto.SyncMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TelegramSenderClientFallback implements TelegramSenderClient {

    private static final Logger log = LoggerFactory.getLogger(TelegramSenderClientFallback.class);

    @Override
    public ResponseEntity<SyncMessageDTO.SendDirectResponse> sendMessage(SyncMessageDTO.SendDirectRequest sendDirectRequest) {
        log.error("Fallback sendMessage in TelegramSenderFallback");
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .build();
    }
}
