package com.leonidas4G.service;

import com.leonidas4G.model.Packet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class PcapService {
    private final PacketService packetService;

    public List<String> parsePcap(String pcapFilePath) {
        final String location_tshark = "\"D:\\Program Files\\Wireshark\\tshark.exe\"";
        String cmd = location_tshark + " -r " + pcapFilePath + " -V";
        List<String> lteList = new ArrayList<>();

        try {
            // Run in Windows command
            Process process = Runtime.getRuntime().exec(cmd);
            // Get input streams
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            // Read command standard output
            String s;
            StringBuilder stringBlock = new StringBuilder();
            while ((s = stdInput.readLine()) != null) {
                if (s.contains("bytes on wire")) {
                    String block = stringBlock.toString();
                    lteList.add(block);
                    stringBlock.delete(0, stringBlock.length());
                }
                stringBlock.append(s);
                stringBlock.append("\n");
            }

            // Read command errors
            while ((s = stdError.readLine()) != null) {
                System.out.println("Standard error: " + s);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return lteList.isEmpty() ? null : lteList;
    }

    public List<Packet> transfer2Pojo(List<String> pcapList) {
        List<Packet> packets = new ArrayList<>();
        HashSet<String> hashSet = new HashSet<>();
        for (String block : pcapList) {
            String[] lines = block.split("\n");
            String pattern, protocol = null, src = null, dst = null, mcc = null,
                    mnc = null, channel = null, timeStr, date = null, time = null;
            Date timeDetail = null;
            int index = 0, length = 0, protocol_line = 0;
            for (String line : lines) {
                if (line.contains("bytes on wire")) {
                    pattern = "(Frame )(\\d+)(: )(\\d+)(\\D*)(\\d+)(.*)";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(line);
                    if (m.find()) {
                        index = Integer.parseInt(m.group(2));
                        length = Integer.parseInt(m.group(6));
                    }
                }
                if (line.contains("Arrival Time:")) {
                    pattern = "(Arrival Time: )(.*?)( 罗马)(.*)";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(line);
                    if (m.find()) {
                        timeStr = m.group(2);
                        timeStr = timeStr.substring(0, timeStr.length() - 6);
                        SimpleDateFormat parseString = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss.S", Locale.ENGLISH);
                        try {
                            timeDetail = parseString.parse(timeStr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss.S", Locale.ENGLISH);
                        time = formatTime.format(timeDetail);
                        SimpleDateFormat formatDate = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
                        date = formatDate.format(timeDetail);
                    }
                }
                if (line.contains("Src:")) {
                    pattern = "(.*?Src: )(.*)(, Dst: )(.*)";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(line);
                    if (m.find()) {
                        src = m.group(2);
                        dst = m.group(4);
                    }
                }
                if (line.contains("Channel:")) {
                    pattern = "(.*?Channel: )([a-zA-Z]*)(.*)";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(line);
                    if (m.find())
                        channel = m.group(2);
                }
                if (line.length() > 0 && line.charAt(0) != ' ') {
                    protocol = line;
                }
                if (line.contains("Mobile Country Code (MCC): ")) {
                    pattern = "(Mobile Country Code \\(MCC\\): )(.*)";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(line);
                    if (m.find())
                        mcc = m.group(2);
                }
                if (line.contains("Mobile Network Code (MNC): ")) {
                    pattern = "(Mobile Network Code \\(MNC\\): )(.*)";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(line);
                    if (m.find())
                        mnc = m.group(2);
                }
            }

            if (protocol != null) {
                if (protocol.startsWith("GSM") ||
                    protocol.startsWith("Non-Access-Stratum"))
                    protocol = "GSM";
                else if (protocol.startsWith("LTE"))
                    protocol = "LTE";
                else if (protocol.trim().equals("BCCH-BCH-Message") ||
                        protocol.trim().equals("UL-CCCH-Message") ||
                        protocol.trim().equals("DL-CCCH-Message") ||
                        protocol.trim().equals("UL-DCCH-Message") ||
                        protocol.trim().equals("DL-DCCH-Message") ||
                        protocol.trim().equals("PCCH-Message"))
                    protocol = "RRC";
                else {
                    System.out.println("other protocol" + protocol);
                }
                if (!hashSet.contains(protocol)) {
                    hashSet.add(protocol.trim());
                }
            }

            Packet packet = packetService.create(protocol, src, dst, mcc, mnc, channel, timeDetail, index, length, time, date);
//            Packet packet = new Packet(index, time, protocol, length, src, dst, mcc, mnc, channel);
            packets.add(packet);
        }
        return packets;
    }
}
