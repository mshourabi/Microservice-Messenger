package com.github.mshourabi.distributor.mapper;

import com.github.mshourabi.client.enums.AggregateType;
import com.github.mshourabi.distributor.model.entity.Message;
import com.github.mshourabi.distributor.model.entity.OutboxEvent;
import com.google.gson.Gson;

import java.util.UUID;

public class OutboxMapper {

    public static OutboxEvent map(Message message) {
        OutboxEvent outbox = new OutboxEvent();
        outbox.setAggregateType(AggregateType.MESSAGE);
        outbox.setAggregateId(String.valueOf(message.getId()));
        outbox.setOutboxId(UUID.randomUUID().toString());
        outbox.setPayload((new Gson()).toJson(message));
        return outbox;
    }
}
