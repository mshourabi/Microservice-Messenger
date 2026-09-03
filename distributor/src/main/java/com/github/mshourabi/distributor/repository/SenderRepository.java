package com.github.mshourabi.distributor.repository;

import com.github.mshourabi.distributor.model.entity.Sender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SenderRepository extends JpaRepository<Sender, Long> {


    Optional<Sender> findByName(String name);
}
