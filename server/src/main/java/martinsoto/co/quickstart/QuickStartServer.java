package martinsoto.co.quickstart;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class QuickStartServer {
    private static final int PORT = 4545;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        try {
            Socket socket = serverSocket.accept();
            (new TaskHandler(socket)).start();
        } finally {
            serverSocket.close();
        }

    }
}
