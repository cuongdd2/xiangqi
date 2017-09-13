import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
    private static final int Port = 14120;

    public static void main(String[] args) {
        startServer();
    }

    private static void startServer() {
        try (
            ServerSocket listener = new ServerSocket(Port);
            Socket clientSocket = listener.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            while (true) {
                String msg = in.readLine();
                System.out.println("Server <---- " + msg);
                if (msg != null) {
                    String[] arr = msg.split(":");
                    switch (arr[0]) {
                        case "m":
                            int id = Integer.parseInt(arr[1]);
                            System.out.println(id);
                            out.println(msg);
                            break;

                        case "S":
                            break;

                        default:
                            System.out.println("Unknown msg: " + msg);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
