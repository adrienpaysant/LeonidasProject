import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JavaRunCommand {
    public static void main(String args[]) {
        final String location_tshark = "\"D:\\Program Files\\Wireshark\\tshark.exe\"";
        final String location_file = "\"D:\\Desktop\\tcpdump.pcap\"";
        String cmd = location_tshark + " -r " + location_file;
        List<String> lteList = new ArrayList<>();

        try {
            // Run "netsh" Windows command
            Process process = Runtime.getRuntime().exec(cmd);

            // Get input streams
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // Read command standard output
            String s;
            System.out.println("Standard output: ");
            while ((s = stdInput.readLine()) != null) {
                if (s.contains("LTE")) {
                    lteList.add(s);
                    System.out.println(s);
                }
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
}