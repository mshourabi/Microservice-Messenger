package com.github.mshourabi.distributor.model.entity;

import com.github.mshourabi.client.entity.BaseEntity;
import com.github.mshourabi.client.enums.MessageStatus;
import com.github.mshourabi.client.enums.SendingStrategy;
import jakarta.persistence.*;

import java.util.List;

@Table(name = "tbl_message")
@Entity
public class Message extends BaseEntity {

    @Column(name = "reference_id", unique = true, nullable = false)
    private String referenceId;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(name="creator_username", nullable = false)
    private String creatorUsername;

    @Column(name = "receiver_identifier", nullable = false)
    private String receiverIdentifier;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    @Column(name="sending_strategy", nullable = false)
    @Enumerated(EnumType.STRING)
    private SendingStrategy sendingStrategy;

    @ManyToMany
    @OrderColumn(name = "sender_order")
    @JoinTable(
            name = "tbl_message_sender",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "sender_id")
    )
    private List<Sender> senders;


    public Message() {
    }

    public Message(String content, String receiverIdentifier) {
        this.content = content;
        this.receiverIdentifier = receiverIdentifier;
    }

    public Message(String referenceId, String content, String creatorUsername, String receiverIdentifier,
                   MessageStatus status, SendingStrategy sendingStrategy, List<Sender> senders) {
        this.referenceId = referenceId;
        this.content = content;
        this.creatorUsername = creatorUsername;
        this.receiverIdentifier = receiverIdentifier;
        this.status = status;
        this.sendingStrategy = sendingStrategy;
        this.senders = senders;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public String getReceiverIdentifier() {
        return receiverIdentifier;
    }

    public void setReceiverIdentifier(String receiverIdentifier) {
        this.receiverIdentifier = receiverIdentifier;
    }

    public List<Sender> getSenders() {
        return senders;
    }

    public void setSenders(List<Sender> senders) {
        this.senders = senders;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public SendingStrategy getSendingType() {
        return sendingStrategy;
    }

    public void setSendingType(SendingStrategy sendingStrategy) {
        this.sendingStrategy = sendingStrategy;
    }
}
