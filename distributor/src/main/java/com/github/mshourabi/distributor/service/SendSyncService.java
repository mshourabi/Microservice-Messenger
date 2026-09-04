package com.github.mshourabi.distributor.service;

import com.github.mshourabi.client.enums.Platform;
import com.github.mshourabi.client.telegramsender.dto.SyncMessageDTO;
import com.github.mshourabi.client.telegramsender.fiegn.TelegramSenderClient;
import com.github.mshourabi.distributor.model.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SendSyncService implements SendingService {
    private static final Logger log = LoggerFactory.getLogger(SendSyncService.class);
    private final TelegramSenderClient telegramSenderClient;

    public SendSyncService(TelegramSenderClient telegramSenderClient) {
        this.telegramSenderClient = telegramSenderClient;
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
                new SyncMessageDTO.SendDirectRequest(message.getContent(), message.getReceiverIdentifier());
        telegramSenderClient.sendMessage(sendDirectRequest);
        log.info("Send Sync Message to telegram");
        System.out.println("Send Sync Message to telegram");
    }
}
