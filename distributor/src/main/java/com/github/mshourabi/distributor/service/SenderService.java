package com.github.mshourabi.distributor.service;

import com.github.mshourabi.distributor.model.dto.SenderDTO;
import com.github.mshourabi.distributor.model.entity.Sender;

import java.util.List;
import java.util.Optional;

public interface SenderService {


    Optional<Sender> findById(Long id);

    Sender findSenderById(Long id);

    SenderDTO.Info findSenderInfoById(Long id);

    List<Sender> findAll();

    Long create(SenderDTO.CreateRequest createRequest);

}
