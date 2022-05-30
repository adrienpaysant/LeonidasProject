import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;

import java.io.EOFException;
import java.util.concurrent.TimeoutException;

@SuppressWarnings("javadoc")
public class PcapTest {

    private static final int COUNT = 5;

    private static final String PCAP_FILE_KEY = "tcpdump.pcapFile";
    private static final String PCAP_FILE = "D:\\project\\spring-boot-react-mysql\\spring-boot-server\\src\\main\\resources\\test.pcap";
    private static final String PCAP_FILE_1 =
            System.getProperty(PCAP_FILE_KEY,
                    "D:\\project\\spring-boot-react-mysql\\spring-boot-server\\src\\main\\resources\\tcpdump.pcap");

    private PcapTest() {
    }

    public static void main(String[] args) throws PcapNativeException, NotOpenException {
        PcapHandle handle;
        try {
            handle = Pcaps.openOffline(PCAP_FILE);
        } catch (PcapNativeException e) {
            handle = Pcaps.openOffline(PCAP_FILE);
        }

        for (int i = 0; i < COUNT; i++) {
            try {
                Packet packet = handle.getNextPacketEx();
                System.out.println(handle.getTimestamp());
                System.out.println(packet);
                System.out.println(handle);
            } catch (TimeoutException e) {
            } catch (EOFException e) {
                System.out.println("EOF");
                break;
            }
        }

        handle.close();
    }
}