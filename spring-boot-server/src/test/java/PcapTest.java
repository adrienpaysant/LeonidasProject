import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("javadoc")
public class PcapTest {

    private PcapTest() {}

    public void ParseTime() {
        String timeStr = "Mar 24, 2018 08:42:25.9600";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss.S", Locale.ENGLISH);
        Date t = null;
        try {
            t = simpleDateFormat.parse(timeStr);
            System.out.println("yes");
        } catch ( ParseException e) {
            System.out.println("Unparseable using " + simpleDateFormat);
        }
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss.S");
        String out = simpleDateFormat1.format(t);
        System.out.println(out);
    }

    public void MatchLine() {
        String line = "Frame 1: 51 bytes on wire (408 bits), 51 bytes captured (408 bits)";
        String pattern = "(Frame )(\\d+)(: )(\\d+)(\\D*)(\\d+)(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(6) );
//            System.out.println("Found value: " + m.group(3) );
        } else {
            System.out.println("NO MATCH");
        }
    }

    public void PrintProtocolNBlock() {
        final String location_tshark = "\"D:\\Program Files\\Wireshark\\tshark.exe\"";
        String pcapFilePath = "\"D:\\Desktop\\tcpdump.pcap\"";
        String cmd = location_tshark + " -r " + pcapFilePath + " -V";
        List<String> lteList = new ArrayList<>();

        try {
            // Run "netsh" Windows command
            Process process = Runtime.getRuntime().exec(cmd);
            // Get input streams
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            // Read command standard output
            String s;
            StringBuilder stringBlock = new StringBuilder();
            HashSet<String> hashSet = new HashSet<>();
            while ((s = stdInput.readLine()) != null) {
                if (s.contains("bytes on wire")) {
                    int line_count = 0;
                    String block = stringBlock.toString();
//                    if (block.contains("LTE")) {
//                    }
                    lteList.add(block);
                    String[] lines = block.split("\n");
                    String protocol = null;
                    for (String line : lines) {
                        if (line.length() > 0 && line.charAt(0) != ' ') {
                            protocol = line;
                        }
                    }
                    if (protocol != null) {
                        if (protocol.startsWith("GSM"))
                            protocol = "GSM";
                        else if (protocol.startsWith("LTE"))
                            protocol = "LTE";
                        else if (protocol.trim().equals("BCCH-BCH-Message") ||
                                protocol.trim().equals("UL-CCCH-Message") ||
                                protocol.trim().equals("DL-CCCH-Message") ||
                                protocol.trim().equals("UL-DCCH-Message") ||
                                protocol.trim().equals("DL-DCCH-Message"))
                            protocol = "RRC";
                        if (!hashSet.contains(protocol)) {
                            hashSet.add(protocol.trim());
                            System.out.println(protocol);
                        }
                    }
//                    System.out.println(block);
//                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
//                            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
//                            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    stringBlock.delete(0, stringBlock.length());
                }
                stringBlock.append(s);
                stringBlock.append("\n");
            }

            // Read command errors
            System.out.println("Standard error: ");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }


    public static void main(String[] args) throws PcapNativeException, NotOpenException {
        PcapTest pcapTest = new PcapTest();
        pcapTest.PrintProtocolNBlock();
    }
}