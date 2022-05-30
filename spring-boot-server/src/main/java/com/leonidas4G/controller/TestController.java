package com.leonidas4G.controller;

import com.leonidas4G.service.FileService;
import lombok.RequiredArgsConstructor;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {
    private final FileService fileService;

    @PostMapping("/uploadFile")
    public void update(@RequestParam(value = "file", required = false) MultipartFile file,
                       HttpServletRequest request) throws IOException, NotOpenException, PcapNativeException {
        String original_name = file.getOriginalFilename();
        File f;
        assert original_name != null;
        if (original_name.endsWith(".pcap") || original_name.endsWith(".pcapng")) {
            f = new File("D:\\Desktop\\ri50.pcap");
            file.transferTo(f);
            fileService.handlePcap(f.getAbsolutePath());
            return;
        } else {
            System.out.println("not correct type");
            f = new File("D:\\Desktop\\ri50.txt");
            file.transferTo(f);
        }
        try (
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
        ) {
            while (true) {
                String line = br.readLine();
                if (null == line)
                    break;
                System.out.println(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
