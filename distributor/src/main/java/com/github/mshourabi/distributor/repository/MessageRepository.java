package com.github.mshourabi.distributor.repository;

import com.github.mshourabi.distributor.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findByReferenceId(String referenceId);
}
