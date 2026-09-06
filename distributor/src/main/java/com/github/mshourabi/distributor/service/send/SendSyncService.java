package com.github.mshourabi.distributor.service.send;

import com.github.mshourabi.client.enums.Platform;
import com.github.mshourabi.client.exceptions.DomainException;
import com.github.mshourabi.client.tekegramagent.dto.SyncMessageDTO;
import com.github.mshourabi.client.tekegramagent.fiegn.TelegramAgentClient;
import com.github.mshourabi.distributor.model.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SendSyncService implements SendingService {
    private static final Logger log = LoggerFactory.getLogger(SendSyncService.class);
    private final TelegramAgentClient telegramAgentClient;

    public SendSyncService(TelegramAgentClient telegramAgentClient) {
        this.telegramAgentClient = telegramAgentClient;
    }

    @Override
    public Message sending(Message message) {

        Platform platform = Platform.TELEGRAM;
        switch (platform) {
            case Platform.TELEGRAM -> sendViaTelegram(message);
            default -> sendViaTelegram(message);
        }
        return message;
    }


    private void sendViaTelegram(Message message) {
        SyncMessageDTO.SendDirectRequest sendDirectRequest =
                new SyncMessageDTO.SendDirectRequest(message.getContent(), message.getReceiverIdentifier(), message.getReferenceId());
        ResponseEntity<SyncMessageDTO.SendDirectResponse> syncMessageResponse = telegramAgentClient.sendMessage(sendDirectRequest);

        if (syncMessageResponse.getStatusCode().is5xxServerError()) {
            log.error("Can not Send Sync Message to telegram.");
            throw new DomainException("An Error Occurred please try later.");
        } else {
            log.info("Send Sync Message to telegram.");
        }
    }
}
