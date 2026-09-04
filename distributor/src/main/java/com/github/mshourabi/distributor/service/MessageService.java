package com.github.mshourabi.distributor.service;

import com.github.mshourabi.distributor.model.dto.MessageDTO;
import com.github.mshourabi.distributor.model.entity.Message;

import java.util.Optional;

public interface MessageService {

    Optional<Message> findById(Long id);

    MessageDTO.Info findMessageById(Long id);

    Message findMessageByReferenceId(String referenceId);

    MessageDTO.Info send(MessageDTO.CreateRequest createRequest);
}
