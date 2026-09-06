package com.github.mshourabi.distributor.service.send;


import com.github.mshourabi.distributor.model.entity.Message;

public interface SendingService {


    /**
     * Save message in db to saved in queue
     *
     * @param message
     * @return
     */
    Message sending(Message message);
}
