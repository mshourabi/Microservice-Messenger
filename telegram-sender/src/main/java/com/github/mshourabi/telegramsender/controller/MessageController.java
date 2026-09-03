package com.github.mshourabi.telegramsender.controller;


import com.github.mshourabi.client.telegramsender.dto.MessageDTO;
import com.github.mshourabi.telegramsender.config.ApiConstants;
import com.github.mshourabi.telegramsender.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping(ApiConstants.MESSAGES)
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }


    @Operation(operationId = "Send a Message")
    @PostMapping()
    public ResponseEntity<MessageDTO.SendResponse> createMessage(@Validated @RequestBody MessageDTO.SendRequest sendRequest) {
        MessageDTO.SendResponse sendResponse = service.sendMessage(sendRequest);
        return ResponseEntity.ok().body(sendResponse);
    }
}