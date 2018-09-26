package martinsoto.co.quickstart;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TaskHandler extends Thread {
    private Socket socket;

    private int stdInOutPort = 0;
    private Socket stdInOutSocket;

    private int stdErrPort = 0;
    private Socket stdErrSocket;

    private class StdInOutWaiter extends Thread {
        private ServerSocket serverSocket;

        StdInOutWaiter() throws IOException {
            serverSocket = new ServerSocket(0);
            stdInOutPort = serverSocket.getLocalPort();
        }

        @Override
        public void run() {
            try {
                try {
                    stdInOutSocket = serverSocket.accept();
                } finally {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class StdErrWaiter extends Thread {
        private ServerSocket serverSocket;

        StdErrWaiter() throws IOException {
            serverSocket = new ServerSocket(0);
            stdErrPort = serverSocket.getLocalPort();
        }

        @Override
        public void run() {
            try {
                try {
                    stdErrSocket = serverSocket.accept();
                } finally {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    TaskHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        OutputStreamWriter writer;
        try {
            (this.new StdInOutWaiter()).start();
            (this.new StdErrWaiter()).start();

            writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write(String.format("StdInOut: %d, StdErr %d\n", stdInOutPort, stdErrPort));
            writer.flush();

            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}