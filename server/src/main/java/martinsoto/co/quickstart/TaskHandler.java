package martinsoto.co.quickstart;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TaskHandler extends Thread {
    private Socket socket;

    TaskHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        OutputStreamWriter writer;
        try {
            writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write("Hello World!\n");
            writer.flush();

            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}