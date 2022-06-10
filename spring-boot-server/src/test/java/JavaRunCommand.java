import com.leonidas4G.model.Packet;
import com.leonidas4G.service.PcapService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JavaRunCommand {
    static public List<Packet> makeToPojo(List<String> lteList) {
        List<Packet> packets = new ArrayList<>();
        for (String sentence : lteList) {

        }
        return packets;
    }

    public static void main(String[] args) {
        final String location_tshark = "\"D:\\Program Files\\Wireshark\\tshark.exe\"";
        final String location_file = "\"D:\\Desktop\\tcpdump.pcap\"";
        String cmd = location_tshark + " -r " + location_file + " -V";

//        PcapService pcapService = new PcapService();
//        List<String> lteList = pcapService.parsePcap(location_file);
//        List<Packet> packetList = pcapService.transfer2Pojo(lteList);
//        System.out.println(packetList.toString());
//
//        try {
//            // Run "netsh" Windows command
//            Process process = Runtime.getRuntime().exec(cmd);
//
//            // Get input streams
//            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//
//            // Read command standard output
//            String s;
//            lteList = new ArrayList<>();
//            StringBuilder stringBlock = new StringBuilder();
//            while ((s = stdInput.readLine()) != null) {
//                if (s.contains("bytes on wire")) {
//                    String block = stringBlock.toString();
//                    if (block.contains("LTE")) {
//                        lteList.add(block);
//                        System.out.println(block);
//                    }
//                    stringBlock.delete(0, stringBlock.length());
//                }
//                stringBlock.append(s);
//                stringBlock.append("\n");
//            }
//
//            // Read command errors
//            System.out.println("Standard error: ");
//            while ((s = stdError.readLine()) != null) {
//                System.out.println(s);
//            }
//        } catch (Exception e) {
//            e.printStackTrace(System.err);
//        }
    }
}