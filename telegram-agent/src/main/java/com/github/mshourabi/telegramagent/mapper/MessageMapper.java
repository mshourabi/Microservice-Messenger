package com.github.mshourabi.telegramagent.mapper;

import com.github.mshourabi.client.tekegramagent.dto.SyncMessageDTO;
import com.github.mshourabi.telegramagent.model.entity.Message;

public class MessageMapper {

    /**
     *
     * @param sendRequest
     * @return
     */
    public static Message map(SyncMessageDTO.SendDirectRequest sendRequest) {
        return new Message(sendRequest.content(), sendRequest.receiverIdentifier());
    }
}
