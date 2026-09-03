package com.github.mshourabi.telegramsender.service;

import com.github.mshourabi.client.telegramsender.dto.MessageDTO;
import com.github.mshourabi.telegramsender.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageServiceImpl implements MessageService {
    private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    private MessageRepository repository;

    public MessageServiceImpl(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public MessageDTO.SendResponse sendMessage(MessageDTO.SendRequest sendRequest) {
        log.trace("sendMessage");
        return null;
    }
}
