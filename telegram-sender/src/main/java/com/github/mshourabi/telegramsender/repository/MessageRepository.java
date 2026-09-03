package com.github.mshourabi.telegramsender.repository;

import com.github.mshourabi.telegramsender.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {


}
