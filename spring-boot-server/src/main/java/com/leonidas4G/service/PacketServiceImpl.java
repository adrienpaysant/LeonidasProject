package com.leonidas4G.service;

import com.leonidas4G.model.Packet;
import com.leonidas4G.repository.PacketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PacketServiceImpl implements PacketService{
    private final PacketRepository packetRepository;

    @Override
    public Packet create(String protocol, String src, String dst, String mcc, String mnc, String channel, String time, int index, int length) {
        Packet tempPacket = new Packet(index, time, protocol, length, src, dst, mcc, mnc, channel);
        packetRepository.save(tempPacket);
        return tempPacket;
    }

    @Override
    public void deleteAll() {
        packetRepository.deleteAll();
    }

    @Override
    public List<Packet> findAll() {
        return packetRepository.findAll();
    }

    @Override
    public void deleteAllPrbs() {
        packetRepository.deleteAll();
    }
}
