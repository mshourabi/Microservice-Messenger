package com.github.mshourabi.distributor.service;

import com.github.mshourabi.client.enums.MessageStatus;
import com.github.mshourabi.client.enums.SendingStrategy;
import com.github.mshourabi.client.exceptions.ResourceNotFoundException;
import com.github.mshourabi.distributor.model.dto.MessageDTO;
import com.github.mshourabi.distributor.model.entity.Message;
import com.github.mshourabi.distributor.model.entity.Sender;
import com.github.mshourabi.distributor.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);
    private final SenderService senderService;
    private final SendingThroughQueueService queueService;
    private final SendingDirectService sendingDirectService;
    private final MessageRepository repository;

    public MessageServiceImpl(MessageRepository repository, SenderService senderService,
                              SendingThroughQueueService queueService, SendingDirectService sendingDirectService) {
        this.repository = repository;
        this.senderService = senderService;
        this.queueService = queueService;
        this.sendingDirectService = sendingDirectService;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Message> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public MessageDTO.Info findMessageById(Long id) {
        return findById(id)
                .map(MessageDTO.Info::map)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }


    @Override
    @Transactional(readOnly = true)
    public Message findMessageByReferenceId(String referenceId) {
        return repository.findByReferenceId(referenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    @Override
    public MessageDTO.Info send(MessageDTO.CreateRequest createRequest) {
        List<Sender> senders = new ArrayList<>();
        for (Long senderProviderId : createRequest.senderIds()) {
            senders.add(senderService.findSenderById(senderProviderId));
        }
        Message message = MessageDTO.CreateRequest.map(createRequest);
        message.setSenders(senders);
        message.setReferenceId(UUID.randomUUID().toString());

        if (message.getSendingStrategy().equals(SendingStrategy.SYNC)) {
            message.setStatus(MessageStatus.PENDING);
            queueService.sending(message);
        } else  {
            message.setStatus(MessageStatus.SENDING);
            sendingDirectService.sending(message);
        }
        return MessageDTO.Info.map(message);
    }

}
