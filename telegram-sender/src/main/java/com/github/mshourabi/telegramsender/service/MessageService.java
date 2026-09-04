package com.github.mshourabi.telegramsender.service;

import com.github.mshourabi.client.telegramsender.dto.SyncMessageDTO;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {


    SyncMessageDTO.SendDirectResponse sendMessage(SyncMessageDTO.SendDirectRequest sendDirectRequest);

}
