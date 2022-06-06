import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;

import java.io.EOFException;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("javadoc")
public class PcapTest {

    private static final int COUNT = 5;

    private static final String PCAP_FILE_KEY = "tcpdump.pcapFile";
    private static final String PCAP_FILE = "D:\\project\\spring-boot-react-mysql\\spring-boot-server\\src\\main\\resources\\tcpdump.pcap";
    private static final String PCAP_FILE_1 =
            System.getProperty(PCAP_FILE_KEY,
                    "D:\\project\\spring-boot-react-mysql\\spring-boot-server\\src\\main\\resources\\tcpdump.pcap");

    private PcapTest() {
    }

    public static void main(String[] args) throws PcapNativeException, NotOpenException {
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
}