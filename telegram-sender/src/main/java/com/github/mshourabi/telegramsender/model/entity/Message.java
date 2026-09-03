package com.github.mshourabi.telegramsender.model.entity;


import com.github.mshourabi.client.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "tbl_message")
@Entity
public class Message extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String referenceId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String creatorUsername;

    @Column(nullable = false)
    private String receiverIdentifier;


    public Message() {
    }

    public Message(String content, String receiverIdentifier) {
        this.content = content;
        this.receiverIdentifier = receiverIdentifier;
    }

    public Message(String referenceId, String content, String receiverIdentifier) {
        this.referenceId = referenceId;
        this.content = content;
        this.receiverIdentifier = receiverIdentifier;
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

}
