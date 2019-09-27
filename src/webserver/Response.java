package webserver;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class Response implements BIF.SWE1.interfaces.Response {
    /**
     * @return Returns a writable map of the response headers. Never returns
     * null.
     */
    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    /**
     * @return Returns the content length or 0 if no content is set yet.
     */
    @Override
    public int getContentLength() {
        return 0;
    }

    /**
     * @return Gets the content type of the response.
     */
    @Override
    public String getContentType() {
        return null;
    }

    /**
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
    public int getStatusCode() {
        return 0;
    }

    /**
     * @param status Sets the current status code.
     */
    @Override
    public void setStatusCode(int status) {

    }

    /**
     * @return Returns the status code as string. (200 OK)
     */
    @Override
    public String getStatus() {
        return null;
    }

    /**
     * Adds or replaces a response header in the headers map
     *
     * @param header
     * @param value
     */
    @Override
    public void addHeader(String header, String value) {

    }

    /**
     * @return Returns the Server response header. Defaults to "BIF-SWE1-Server".
     */
    @Override
    public String getServerHeader() {
        return null;
    }

    /**
     * Sets the Server response header.
     *
     * @param server
     */
    @Override
    public void setServerHeader(String server) {

    }

    /**
     * @param content Sets a string content. The content will be encoded in UTF-8.
     */
    @Override
    public void setContent(String content) {

    }

    /**
     * @param content Sets a byte[] as content.
     */
    @Override
    public void setContent(byte[] content) {

    }

    /**
     * @param stream Sets the stream as content.
     */
    @Override
    public void setContent(InputStream stream) {

    }

    /**
     * @param network Sends the response to the network stream.
     */
    @Override
    public void send(OutputStream network) {

    }
}
