package com.github.mshourabi.distributor.service;

import com.github.mshourabi.distributor.model.entity.Message;
import com.github.mshourabi.distributor.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SendingThroughQueueService implements SendingService {

    private final MessageRepository messageRepository;

    public SendingThroughQueueService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Message sending(Message message) {
        return messageRepository.save(message);
    }
}
