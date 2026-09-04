package com.github.mshourabi.telegramsender.service;

import com.github.mshourabi.client.telegramsender.dto.MessageDTO;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {


    MessageDTO.SendDirectResponse sendMessage(MessageDTO.SendDirectRequest sendDirectRequest);

}
