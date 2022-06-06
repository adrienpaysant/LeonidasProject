package com.leonidas4G.controller;

import com.leonidas4G.model.Packet;
import com.leonidas4G.service.PacketService;
import com.leonidas4G.service.PcapService;
import lombok.RequiredArgsConstructor;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/uploadFile")
    public List<Packet> update(@RequestParam(value = "file") MultipartFile file) throws IOException {
        String original_name = file.getOriginalFilename();
        File f;
        assert original_name != null;
        if (original_name.endsWith(".pcap") || original_name.endsWith(".pcapng")) {
            f = new File("D:\\Desktop\\ri50.pcap");
            file.transferTo(f);
            List<String> pcapList = pcapService.parsePcap(f.getAbsolutePath());
            if (!pcapList.isEmpty()) {
                pcapService.transfer2Pojo(pcapList);
                return packetService.findAll();
            }
        } else {
            System.out.println("not correct type");
        }
        return null;
    }

}
