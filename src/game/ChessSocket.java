package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChessSocket {
    private static final String Host = "138.197.92.4";
    Socket s = new Socket(Host, 14120);
    PrintWriter out = new PrintWriter(s.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

    public ChessSocket() throws IOException {

    }
}
