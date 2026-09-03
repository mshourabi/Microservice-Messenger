package com.github.mshourabi.distributor.controller;

import com.github.mshourabi.distributor.config.ApiConstants;
import com.github.mshourabi.distributor.model.dto.MessageDTO;
import com.github.mshourabi.distributor.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController()
@RequestMapping(ApiConstants.MESSAGES)
@Tag(name = "Messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @Operation(operationId = "Get MessageInfo")
    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO.Info> getMessage(@PathVariable Long id) {
        MessageDTO.Info info = service.findMessageById(id);
        return ResponseEntity.ok(info);
    }

    @Operation(operationId = "Create a Message")
    @PostMapping()
    public ResponseEntity<Void> createMessage(@Validated @RequestBody MessageDTO.CreateRequest createRequest) {
        Long id = service.send(createRequest);
        return ResponseEntity.created(URI.create(ApiConstants.MESSAGES + "/" + id)).build();
    }
}
