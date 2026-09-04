//package com.github.mshourabi.telegramsender.fiegn;
//
//import com.github.mshourabi.client.telegramsender.constants.ApiConstants;
//import com.github.mshourabi.client.telegramsender.dto.MessageDTO;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@FeignClient("${spring.application.name}")
//public interface MessageClient {
//
//    @PostMapping(ApiConstants.SEND_SYNC)
//    ResponseEntity<MessageDTO.SendDirectResponse> createMessage(@RequestBody MessageDTO.SendDirectRequest sendRequest);
//}
