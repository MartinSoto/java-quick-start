package martinsoto.co;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class QuickStartServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;
        int port = 4545;

        serverSocket = new ServerSocket(port);
        try {
            Socket socket = serverSocket.accept();

            try {
                OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
                writer.write("Hello World!\n");
                writer.flush();
            } finally {
                socket.close();
            }
        } finally {
            serverSocket.close();
        }

    }
}
