package com.github.mshourabi.distributor.mapper;

import com.github.mshourabi.client.enums.AggregateType;
import com.github.mshourabi.client.enums.EventType;
import com.github.mshourabi.distributor.model.entity.Message;
import com.github.mshourabi.distributor.model.entity.OutboxEvent;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

public class OutboxMapper {

    public static OutboxEvent map(Message message) {
        ObjectMapper objectMapper = new ObjectMapper();
        OutboxEvent outbox = new OutboxEvent();

        outbox.setAggregateType(AggregateType.MESSAGE);
        outbox.setAggregateId(String.valueOf(message.getId()));
        outbox.setOutboxId(UUID.randomUUID().toString());
        outbox.setPayload(objectMapper.writeValueAsString(message));
        return outbox;
    }
}
