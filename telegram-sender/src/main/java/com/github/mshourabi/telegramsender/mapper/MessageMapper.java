package com.github.mshourabi.telegramsender.mapper;

import com.github.mshourabi.telegramsender.model.entity.Message;

public class MessageMapper {

    /**
     *
     * @param sendRequest
     * @return
     */
    public static Message map(MessageDTO.SendRequest sendRequest) {
        return new Message(sendRequest.content(), sendRequest.receiverIdentifier());
    }
}
