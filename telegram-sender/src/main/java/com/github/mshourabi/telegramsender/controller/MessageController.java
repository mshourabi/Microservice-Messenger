package com.github.mshourabi.telegramsender.controller;


import com.github.mshourabi.client.enums.MessageStatus;
import com.github.mshourabi.client.enums.SendingStrategy;
import com.github.mshourabi.client.telegramsender.constants.ApiConstants;
import com.github.mshourabi.client.telegramsender.dto.SyncMessageDTO;
import com.github.mshourabi.telegramsender.service.MessageService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;


@RestController()
@RequestMapping(ApiConstants.SEND_SYNC)
public class MessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);
    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }


    @Operation(operationId = "Send a Message")
    @PostMapping()
    public ResponseEntity<SyncMessageDTO.SendDirectResponse> sendMessage(@Validated @RequestBody SyncMessageDTO.SendDirectRequest sendDirectRequest) {
        SyncMessageDTO.SendDirectResponse sendResponse = service.sendMessage(sendDirectRequest);
        return ResponseEntity.ok().body(sendResponse);
    }

    @Operation(operationId = "Get Message")
    @GetMapping("/{referenceId}")
    @Retry(name = "getMessageFromTelegramSender", fallbackMethod = "getMessageFallBackMethod")
    public ResponseEntity<SyncMessageDTO.Info> getMessage(@PathVariable String referenceId) {

        SyncMessageDTO.Info info = new SyncMessageDTO.Info(
                1L, referenceId, "A test message", "receiverIdentifier",
                MessageStatus.RECEIVED, SendingStrategy.IN_ORDER, Instant.now(), Instant.now());
        return ResponseEntity.ok().body(info);
    }

    /**
     * FallBack Method
     *
     * @param referenceId
     * @param ex
     * @return
     */
    public ResponseEntity<SyncMessageDTO.Info> getMessageFallBackMethod(@PathVariable String referenceId, Throwable ex) {
        log.error("Can not call getMessageFromTelegramSender.", ex);
        return ResponseEntity.ok().body(null);
    }


}