package webserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Response implements BIF.SWE1.interfaces.Response {

    private int statusCode = 0;
    private Map<String, String> headers = new HashMap<>();
    private String content = null;
    private byte[] contentByte = null;

    public Response() {
        this.headers.put("server", "BIF-SWE1-Server");
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
        if (this.content == null) return 0;
        return this.content.length();
    }

    /**
     * ToDo: implement
     * @return Gets the content type of the response.
     */
    @Override
    public String getContentType() {
        return null;
    }

    /**
     * ToDo: implement
     * @param contentType Sets the content type of the response.
     * @throws IllegalStateException A specialized implementation may throw a
     *                               InvalidOperationException when the content type is set by the
     *                               implementation.
     */
    @Override
    public void setContentType(String contentType) {

    }

    /**
     * @return Gets the current status code. An Exceptions is thrown, if no status code was set.
     */
    @Override
    public int getStatusCode() throws IOException {
        if (this.statusCode == 0) throw new IOException("No Statuscode set");
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
     * @param value the header-"value"
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
        return this.headers.get("server");
    }

    /**
     * Sets the Server response header.
     *
     * @param server the value for the header-"key" "server"
     */
    @Override
    public void setServerHeader(String server) {
        this.headers.put("server", server);
    }

    /**
     * @param content Sets a string content. The content will be encoded in UTF-8.
     */
    @Override
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @param content Sets a byte[] as content.
     */
    @Override
    public void setContent(byte[] content) {
        this.contentByte = content;
    }

    /**
     * ToDo: implement
     * @param stream Sets the stream as content.
     */
    @Override
    public void setContent(InputStream stream) {

    }

    /**
     * ToDo: implement
     * @param network Sends the response to the network stream.
     */
    @Override
    public void send(OutputStream network) {

    }
}
