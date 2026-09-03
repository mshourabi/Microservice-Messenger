package com.github.mshourabi.distributor.controller;

import com.github.mshourabi.distributor.config.ApiConstants;
import com.github.mshourabi.distributor.model.dto.SenderDTO;

import com.github.mshourabi.distributor.service.SenderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController()
@RequestMapping(ApiConstants.SENDERS)
@Tag(name = "Senders")
public class SenderController {

    private SenderService service;


    public SenderController(SenderService service) {
        this.service = service;
    }


    @Operation(summary = "Get SenderInfo")
    @GetMapping("/{id}")
    public ResponseEntity<SenderDTO.Info> getSenderInfo(@PathVariable Long id) {
        SenderDTO.Info info = service.findSenderInfoById(id);
        return ResponseEntity.ok(info);
    }


    @Operation(operationId = "Create a Sender")
    @PostMapping()
    public ResponseEntity<Long> createSenderInfo(@Validated @RequestBody SenderDTO.CreateRequest createRequest) {
        Long id = service.create(createRequest);
        return ResponseEntity.created(URI.create(ApiConstants.SENDERS + "/" + id)).build();
    }
}
