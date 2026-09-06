package com.github.mshourabi.telegramagent.controller;


import com.github.mshourabi.client.enums.MessageStatus;
import com.github.mshourabi.client.enums.SendingStrategy;
import com.github.mshourabi.client.tekegramagent.constants.ApiConstants;
import com.github.mshourabi.client.tekegramagent.dto.SyncMessageDTO;
import com.github.mshourabi.telegramagent.service.MessageService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
    @Retry(name = "getMessageFromTelegramAgent", fallbackMethod = "getMessageFallBackMethodRetry")
    @RateLimiter(name = "getMessageFromTelegramAgent", fallbackMethod = "getMessageFallBackMethodRateLimiter")
    public ResponseEntity<SyncMessageDTO.Info> getMessage(@PathVariable String referenceId) {

        log.info("The getMessage method Call successfully");

        SyncMessageDTO.Info info = new SyncMessageDTO.Info(
                1L, referenceId, "A test message", "receiverIdentifier",
                MessageStatus.RECEIVED, SendingStrategy.IN_ORDER, Instant.now(), Instant.now());
        return ResponseEntity.ok().body(info);
    }

    /**
     * FallBack Method for Retry
     *
     * @param referenceId
     * @param ex
     * @return
     */
    public ResponseEntity<SyncMessageDTO.Info> getMessageFallBackMethodRetry(
            @PathVariable String referenceId, Throwable ex) {

        log.error("Can not getMessageFromTelegramAgent.", ex);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }


    /**
     * FallBack Method for RateLimiter
     *
     * @param referenceId
     * @param ex
     * @return
     */
    public ResponseEntity<SyncMessageDTO.Info> getMessageFallBackMethodRateLimiter(
            @PathVariable String referenceId, Throwable ex) {

        log.error("To Many Request.", ex);
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
    }
}