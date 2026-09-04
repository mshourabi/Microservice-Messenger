package com.github.mshourabi.telegramsender.service;

import com.github.mshourabi.client.telegramsender.dto.SyncMessageDTO;
import com.github.mshourabi.telegramsender.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final MessageRepository repository;

    public MessageServiceImpl(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public SyncMessageDTO.SendDirectResponse sendMessage(SyncMessageDTO.SendDirectRequest sendDirectRequest) {
        log.trace("sendMessage");
        return null;
    }
}
