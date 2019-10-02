package webserver;

import java.net.ServerSocket;
import java.util.Date;

public class HTTPServer { // Runnable, so it can be started as a separate thread for each client

    private static final int PORT = 8080; // port to listen connection
    private static final boolean verbose = true; // verbose mode for extended console logging

    public static void main(String[] args) {
        try {
            if (PORT < 1) throw new Exception("Port not in Range");

            ServerSocket socket = new ServerSocket(PORT);
            System.out.println("Server started.\nListening for connections on port " + PORT + " ...\n");

            // infinite loop to listen on port
            while (!socket.isClosed()) {
                HTTPServerInstance serverInstance = new HTTPServerInstance(socket.accept()); // wait for new incoming connection

                if (verbose) System.out.println("\nNew incoming connection (" + new Date() + "):");

                Thread thread = new Thread(serverInstance); // to enable multi-user access, let the server run in its own thread
                thread.start();
            }
            System.out.println("Server Closed!");

        } catch (Exception e) {
            System.err.println("Server crashed with error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}