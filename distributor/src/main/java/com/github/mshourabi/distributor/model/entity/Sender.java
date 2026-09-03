package com.github.mshourabi.distributor.model.entity;

import com.github.mshourabi.client.entity.BaseEntity;
import com.github.mshourabi.client.enums.Platform;
import jakarta.persistence.*;

import java.util.Set;

@Table(name = "tbl_sender")
@Entity
public class Sender extends BaseEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "platform", nullable = false)
    @Enumerated(EnumType.STRING)
    private Platform platform;

    @ManyToMany(mappedBy = "senders")
    private Set<Message> messages;

    public Sender() {
    }

    public Sender(Platform platform, Set<Message> messages) {
        this.platform = platform;
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
