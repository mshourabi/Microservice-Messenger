package com.github.mshourabi.distributor.model.entity;

import com.github.mshourabi.client.entity.BaseEntity;
import com.github.mshourabi.client.enums.AggregateType;
import com.github.mshourabi.client.enums.EventType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import tools.jackson.databind.JsonNode;

@Table(name = "tbl_outbox_event")
@Entity
public class OutboxEvent extends BaseEntity {

    @Column(unique = true, length = 36)
    private String outboxId;

    @Column(name = "aggregate_type", length = 100)
    @Enumerated(EnumType.STRING)
    private AggregateType aggregateType;

    @Column(name = "aggregate_id", length = 100)
    private String aggregateId;

    @Column(name = "event_type", length = 100)
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payload", columnDefinition = "jsonb", nullable = false)
    private String payload;

    public OutboxEvent() {
    }

    public OutboxEvent(String outboxId, AggregateType aggregateType, String aggregateId, EventType eventType, String payload) {
        this.outboxId = outboxId;
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payload = payload;
    }

    public String getOutboxId() {
        return outboxId;
    }

    public void setOutboxId(String outboxId) {
        this.outboxId = outboxId;
    }

    public AggregateType getAggregateType() {
        return aggregateType;
    }

    public void setAggregateType(AggregateType aggregateType) {
        this.aggregateType = aggregateType;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
