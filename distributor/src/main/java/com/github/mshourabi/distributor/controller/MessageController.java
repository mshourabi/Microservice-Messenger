package com.github.mshourabi.distributor.controller;

import com.github.mshourabi.distributor.config.ApiConstants;
import com.github.mshourabi.distributor.model.dto.MessageDTO;
import com.github.mshourabi.distributor.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MessageDTO.Info> createMessage(@Valid @RequestBody MessageDTO.CreateRequest createRequest) {
        MessageDTO.Info messageInfo = service.send(createRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(messageInfo);
    }
}
