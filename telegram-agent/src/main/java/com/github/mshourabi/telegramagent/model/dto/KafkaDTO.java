package com.github.mshourabi.telegramagent.model.dto;


import com.github.mshourabi.client.enums.AggregateType;
import com.github.mshourabi.client.enums.EventType;

import java.time.Instant;
import java.util.List;

public class KafkaDTO {

    /**
     *
     * @param id
     * @param status
     * @param content
     * @param senders
     * @param version
     * @param createdAt
     * @param referenceId
     * @param lastModifiedAt
     * @param creatorUsername
     * @param sendingStrategy
     * @param receiverIdentifier
     */
    public record Message(Long id,
                          String status,
                          String content,
                          List<Object> senders,
                          Long version,
                          Instant createdAt,
                          String referenceId,
                          Instant lastModifiedAt,
                          String creatorUsername,
                          String sendingStrategy,
                          String receiverIdentifier) {

    }


}
