package com.leonidas4G.service;

import com.leonidas4G.model.Packet;

import java.util.List;

public interface PacketService {
    Packet create(String protocol, String src,
                  String dst, String mcc, String mnc, String channel,
                  String time, int index, int length);

    void deleteAll();

    List<Packet> findAll();
}
