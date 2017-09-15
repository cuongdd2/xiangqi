import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Server {
    private static final int Port = 14120;

    public static void main(String[] args) {
        new Server();
    }

    private boolean hasPlayer = false;
    private List<Integer> playerId;
    private int currentId = -1;

    public Server() {
        startServer();
    }


    private void startServer() {
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
                    int id = Integer.parseInt(arr[1]);
                    switch (arr[0]) {
                        case "join":
                            if (hasPlayer) {
                                out.println("join:0");
                                playerId.add(id);
                                out.println("start:" + currentId);
                            } else {
                                hasPlayer = true;
                                playerId = new ArrayList<>();
                                playerId.add(id);
                                currentId = id;
                                out.println("join:1");
                            }
                            break;
                        default:
                            if (currentId == id) {
                                int idx = playerId.indexOf(id);
                                currentId = idx == 0 ? playerId.get(1) : playerId.get(0);
                                out.println("move:" + arr[2] + ":" + arr[3]);
                            }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
