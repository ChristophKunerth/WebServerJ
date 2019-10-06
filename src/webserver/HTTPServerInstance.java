package webserver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class HTTPServerInstance implements  Runnable {

    private Socket connection; // the current clients connection
    private Request request;
    private Url url;

    HTTPServerInstance(Socket connection) throws IOException {
        this.connection = connection;
        this.request = new Request(this.connection.getInputStream());
    }

    @Override
    public void run() {
        if (!this.request.isValid()) return;
        if (HTTPServer.verbose) {
            System.out.println("\nNew incoming connection (" + new Date() + "):");
            System.out.println("Requested URL: " + this.request.getUrl().getRawUrl());
        }
        try {
            Response res = new Response();
            res.setStatusCode(200);
            res.setContentType("text/html");
//            res.setContentType("image/jpeg");
            res.setContent("<h1>Hello World!</h1>");
//            byte[] fileContent = Files.readAllBytes(Paths.get("test.jpg"));
//            res.setContent(fileContent);
            res.send(connection.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
