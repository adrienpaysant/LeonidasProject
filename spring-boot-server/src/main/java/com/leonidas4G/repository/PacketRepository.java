package com.leonidas4G.repository;

import com.leonidas4G.model.Packet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacketRepository extends JpaRepository<Packet, Long> {

}
