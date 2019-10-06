package webserver;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Response implements BIF.SWE1.interfaces.Response {

    private int statusCode = 0;
    private Map<String, String> headers = new HashMap<>();
    private byte[] contentBytes = null;

    public Response() {
        this.headers.put("Server", "BIF-SWE1-Server");
    }

    /**
     * @return Returns a writable map of the response headers. Never returns
     * null.
     */
    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    /**
     * @return Returns the content length or 0 if no content is set yet.
     */
    @Override
    public int getContentLength() {
        if (this.contentBytes == null) return 0;
        return this.contentBytes.length;
    }

    /**
     * @return Gets the content type of the response.
     */
    @Override
    public String getContentType() {
        if (!this.headers.containsKey("Content-Type")) return null;
        return this.headers.get("Content-Type");
    }

    /**
     * @param contentType Sets the content type of the response.
     * @throws IllegalStateException A specialized implementation may throw a
     *                               InvalidOperationException when the content type is set by the
     *                               implementation.
     */
    @Override
    public void setContentType(String contentType) {
        this.headers.put("Content-Type", contentType);
    }

    /**
     * @return Gets the current status code. An Exceptions is thrown, if no status code was set.
     */
    @Override
    public int getStatusCode() {

        if (this.statusCode == 0) throw new IllegalStateException("No Statuscode set");

        return this.statusCode;
    }

    /**
     * @param code Sets the current status code.
     */
    @Override
    public void setStatusCode(int code) {
        this.statusCode = code;
    }

    /**
     * @return Returns the status code as string. (200 OK)
     */
    @Override
    public String getStatus() {
        switch (this.statusCode) {
            case 200:
                return "200 OK";
            case 404:
                return "404 NOT FOUND";
            case 500:
            default:
                return "500 INTERNAL SERVER ERROR";
        }
    }

    /**
     * Adds or replaces a response header in the headers map
     *
     * @param header the header-"key"
     * @param value  the header-"value"
     */
    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    /**
     * @return Returns the Server response header. Defaults to "BIF-SWE1-Server".
     */
    @Override
    public String getServerHeader() {
        return this.headers.get("Server");
    }

    /**
     * Sets the Server response header.
     *
     * @param server the value for the header-"key" "server"
     */
    @Override
    public void setServerHeader(String server) {
        this.headers.put("Server", server);
    }

    /**
     * @param content Sets a string content. The content will be encoded in UTF-8.
     */
    @Override
    public void setContent(String content) {
        this.contentBytes = content.getBytes();
    }

    /**
     * @param content Sets a byte[] as content.
     */
    @Override
    public void setContent(byte[] content) {
        this.contentBytes = content;
    }

    /**
     * @param stream Sets the stream as content.
     */
    @Override
    public void setContent(InputStream stream) {
        try {
            this.contentBytes = stream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param networkOutputStream Sends the response to the network stream.
     */
    @Override
    public void send(OutputStream networkOutputStream) {
        // To
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(networkOutputStream));
            BufferedOutputStream dataOut = new BufferedOutputStream(networkOutputStream); // binary output stream

            out.write("HTTP/1.1 " + this.getStatus() + "\n");
            this.addHeader("Content-Length", Integer.toString(this.getContentLength()));
            this.addHeader("Content-Type", this.getContentType());
            this.addHeader("Date", new Date() + "");

            for (Map.Entry<String, String> header : this.headers.entrySet()) {
                String h = header.getKey() + ": " + header.getValue() + "\n";
                out.write(h);
            }
            out.write("\n"); // Required to match HTTP protocol specification!! very important!

            out.flush();

            dataOut.write(this.contentBytes);

            dataOut.flush();

            out.close();
            dataOut.close();
            networkOutputStream.close();
        } catch (IOException e) {
            System.out.println("Fehler mit Nachricht: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
