import java.net.InetSocketAddress;
import java.net.Socket;

public class PortScanner {
    public static void main(String[] args) {
        try {
            if ((args.length == 0) || (args[0]).equals("help") || (args[0]).equals("Help")) {
                System.out.println("PortScanner.jar by Roman Yaschenko MO-202\nThe program checks whether TCP ports are open or closed");
                System.out.println("Run: \njava -jar PortScanner.jar [Port range start]" +
                        "[Port range end][host (If not specified then Localhost)]\n" +
                        "Example: \njava -jar PortScanner.jar 1 " +
                        "2 example.com");
            } else {
                int startPort = Integer.parseInt(args[0]);
                int endPort = Integer.parseInt(args[1]);
                String host;

                if (endPort < startPort) {
                    int temp = endPort;
                    endPort = startPort;
                    startPort = temp;
                }

                if (args.length == 3)
                    host = args[2];
                else
                    host = "127.0.0.1";

                System.out.println("Host: " + host+"\nCheck has been started");

                for (int port = startPort; port <= endPort; port++) {
                    Boolean isOpen = true;

                    try (Socket socket = new Socket()) {
                        socket.connect(new InetSocketAddress(host, port), 200);
                    } catch (Exception e) {
                        isOpen = false;
                    }

                    if (isOpen)
                        System.out.println("Port " + port + " is open");
                    else
                        System.out.println("Port " + port + " is closed");
                    if (port == endPort) System.out.println("Check has been completed!");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error! Check arguments!");
            System.out.println("Run: \njava -jar PortScanner.jar [Port range start]" +
                    "[Port range end][host (If not specified then Localhost)]\n" +
                    "Example: \njava -jar PortScanner.jar 1 " +
                    "2 example.com");
        }
    }
}
