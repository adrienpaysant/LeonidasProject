package com.leonidas4G.controller;

import com.leonidas4G.model.Packet;
import com.leonidas4G.service.PacketService;
import com.leonidas4G.service.PcapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PcapController {
    private final PcapService pcapService;
    private final PacketService packetService;

    @PostMapping("/pcapData")
    public String update(@RequestParam(value = "file") MultipartFile file) throws IOException {
        String original_name = file.getOriginalFilename();
        File f;
        assert original_name != null;
        if (original_name.endsWith(".pcap") || original_name.endsWith(".pcapng")) {
            f = new File("D:\\Desktop\\ri50.pcap");
            file.transferTo(f);
            List<String> pcapList = pcapService.parsePcap(f.getAbsolutePath());
            List<Packet> packets = pcapService.transfer2Pojo(pcapList);
            if (!packets.isEmpty()) {
                return "success";
            }
        } else {
            System.out.println("not correct type");
        }
        return "failed";
    }

    @GetMapping("/pcapData")
    public ResponseEntity<List<Packet>> getPacket() {
        try {
            List<Packet> packets = packetService.findAll();

            if (packets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(packets, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/packetData")
    public ResponseEntity<HttpStatus> deleteAllPackets() {
        try {
            packetService.deleteAllPrbs();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
