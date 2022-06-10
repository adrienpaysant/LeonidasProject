package com.leonidas4G.service;

import com.leonidas4G.model.Packet;

import java.util.Date;
import java.util.List;

public interface PacketService {
    Packet create(String protocol, String src,
                  String dst, String mcc, String mnc, String channel,
                  Date timeDetail, int index, int length, String time, String date);

    void deleteAll();

    List<Packet> findAll();

    void deleteAllPrbs();
}
