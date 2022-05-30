package com.leonidas4G.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;
import org.springframework.stereotype.Service;

import java.io.EOFException;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    public void handlePcap(String pcapFile) throws PcapNativeException, NotOpenException {
        PcapHandle handle;
        try {
            handle = Pcaps.openOffline(pcapFile);
        } catch (PcapNativeException e) {
            handle = Pcaps.openOffline(pcapFile);
        }

        for (int i = 0; i < 10; i++) {
            try {
                Packet packet = handle.getNextPacketEx();
                System.out.println(handle.getTimestamp());
                System.out.println(packet);
            } catch (TimeoutException e) {
            } catch (EOFException e) {
                System.out.println("EOF");
                break;
            }
        }

        handle.close();
    }
}
