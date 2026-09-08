package com.github.mshourabi.distributor.service.send;

import com.github.mshourabi.client.enums.EventType;
import com.github.mshourabi.distributor.mapper.OutboxMapper;
import com.github.mshourabi.distributor.model.entity.Message;
import com.github.mshourabi.distributor.model.entity.OutboxEvent;
import com.github.mshourabi.distributor.repository.MessageRepository;
import com.github.mshourabi.distributor.repository.OutboxEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SendAsyncService implements SendingService {

    private final MessageRepository messageRepository;
    private final OutboxEventRepository outboxRepository;

    public SendAsyncService(MessageRepository messageRepository, OutboxEventRepository outboxRepository) {
        this.messageRepository = messageRepository;
        this.outboxRepository = outboxRepository;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Message sending(Message message) {
        // todo Get from Spring Security
        message.setCreatorUsername("USER");

        message = messageRepository.save(message);
        OutboxEvent outbox = OutboxMapper.map(message);
        outbox.setEventType(EventType.TELEGRAM_AYSNC_MESSAGE);
        outboxRepository.save(outbox);
        return message;
    }
}
