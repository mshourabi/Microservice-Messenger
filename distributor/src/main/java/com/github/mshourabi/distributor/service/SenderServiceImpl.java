package com.github.mshourabi.distributor.service;

import com.github.mshourabi.client.enums.Platform;
import com.github.mshourabi.client.exceptions.DuplicateException;
import com.github.mshourabi.client.exceptions.ResourceNotFoundException;
import com.github.mshourabi.distributor.model.dto.SenderDTO;
import com.github.mshourabi.distributor.model.entity.Sender;
import com.github.mshourabi.distributor.repository.SenderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SenderServiceImpl implements SenderService {

    private static final Logger log = LoggerFactory.getLogger(SenderServiceImpl.class);
    private final SenderRepository repository;

    public SenderServiceImpl(SenderRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Sender> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Sender findSenderById(Long id) {
        return findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public SenderDTO.Info findSenderInfoById(Long id) {
        return findById(id).map(SenderDTO.Info::map).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sender> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Long create(SenderDTO.CreateRequest createRequest){
        Sender sender = SenderDTO.CreateRequest.map(createRequest);
        Optional<Sender> optional = repository.findByName(sender.getName());
        if (optional.isPresent()) {
            sender.setPlatform(Platform.SMS);
            return repository.save(sender).getId();
        }
        throw new DuplicateException(sender.getName(), "Sender with name '" + sender.getName() + "' already exists");
    }
}
