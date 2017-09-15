package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChessSocket {
//    private static final String Host = "138.197.92.4";
    private static final String Host = "10.10.13.86";
    private Socket s = new Socket(Host, 14120);
    private PrintWriter out = new PrintWriter(s.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

    public ChessSocket() throws IOException {

    }

    public void sendMsg(String msg) {
        System.out.println("Client ----> : " + msg);
        out.println(msg);
    }
}
