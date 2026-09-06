package com.github.mshourabi.telegramagent.service;

import com.github.mshourabi.client.tekegramagent.dto.SyncMessageDTO;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {


    SyncMessageDTO.SendDirectResponse sendMessage(SyncMessageDTO.SendDirectRequest sendDirectRequest);

}
