package webserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class HTTPServerInstance implements  Runnable {

    private Socket connection; // the current clients connection
    private Request request;
    private Url url;

    HTTPServerInstance(Socket connection) throws IOException {
        this.connection = connection;
        this.request = new Request(this.connection.getInputStream());
        this.url = this.request.getUrl();
    }

    @Override
    public void run() {
        try {
            // System.out.println("Requested URL via " + this.request.getMethod() + " method: " + this.url.getRawUrl());
            PrintWriter out = new PrintWriter(this.connection.getOutputStream()); // character output stream
            out.println("HTTP/1.1 200 OK");
            out.println("Content-length: 0");
            out.println("Server: Java HTTP Server by Semeon Funck 1.0");
            out.println("Date: " + new Date().toString());
            out.println("Content-type: text/plain");
            out.println(); // Required to match HTTP protocol specification!! very important!
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
