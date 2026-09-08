package com.github.mshourabi.telegramagent.kafka;

import com.github.mshourabi.telegramagent.model.dto.KafkaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class TelegramMessageKafkaListener {
    private static final Logger log = LoggerFactory.getLogger(TelegramMessageKafkaListener.class);

    private final ObjectMapper objectMapper;

    public TelegramMessageKafkaListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topics = "TELEGRAM_AYSNC_MESSAGE",
            groupId = "telegram-agent"
    )
    public void consume(String payload) {

        log.info("Raw payload: {}", payload);

        String json = objectMapper.readValue(payload, String.class);

        KafkaDTO.Message message =
                objectMapper.readValue(json, KafkaDTO.Message.class);

        log.info(
                "Received telegram message. id={}, recipient={}",
                message.id(),
                message.receiverIdentifier()
        );

        // ارسال پیام به Telegram
        // telegramService.send(message);
    }

}
