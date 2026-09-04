package com.github.mshourabi.telegramsender.controller;


import com.github.mshourabi.client.telegramsender.constants.ApiConstants;
import com.github.mshourabi.client.telegramsender.dto.SyncMessageDTO;
import com.github.mshourabi.telegramsender.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping(ApiConstants.SEND_SYNC)
public class MessageController {

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
}