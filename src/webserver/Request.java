package webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Request implements BIF.SWE1.interfaces.Request {

    private static final String[] ACCEPTED_METHODS = {"GET", "POST", "HEAD"};

    private InputStream inputStream;
    private String method = null;
    private webserver.Url url = null;
    private Map<String, String > headers = new HashMap<>();

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.inputStream));
            String line = in.readLine();
            if (line == null) { // sometimes the Browser is sending an empty request?!
                // this.method = "GET";
                // this.url = new Url("/");
                return;
            }
            StringTokenizer parser = new StringTokenizer(line); // use std. delimiters (new-line, space, tab, ...)
            if (parser.hasMoreTokens()) this.method = parser.nextToken().toUpperCase(); // HTTP method of the client
            if (parser.hasMoreTokens()) this.url = new webserver.Url(parser.nextToken());

            while ((line = in.readLine()) != null) {
                // parse Headers
                parser = new StringTokenizer(line, ": ");
                if (!parser.hasMoreTokens()) break;
                String key = parser.nextToken().toLowerCase();
                if (!parser.hasMoreTokens()) throw new IOException("Not a valid HTTP request");
                StringBuilder val = new StringBuilder(parser.nextToken());
                while (parser.hasMoreTokens()) val.append(" ").append(parser.nextToken()); // Like in user-agent there are sometimes values containing whitespaces
                this.headers.put(key, val.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean methodAllowed(String method) {
        if (method == null || method.isEmpty()) return false;
        for (String haystack: ACCEPTED_METHODS) if (method.toUpperCase().equals(haystack.toUpperCase())) return true;
        return false;
    }

    /**
     * @return Returns true if the request is valid. A request is valid, if
     * method and url could be parsed. A header is not necessary.
     */
    @Override
    public boolean isValid() {
        if (!this.methodAllowed(this.method)) return false;
        return (this.url != null);
    }

    /**
     * @return Returns the request method in UPPERCASE. get -> GET
     */
    @Override
    public String getMethod() {
        return this.method;
    }

    /**
     * @return Returns a URL object of the request. Never returns null.
     */
    @Override
    public webserver.Url getUrl() { // was zum f*ck soll das denn dann returnen, wenn der request nicht valide ist?
        return (this.isValid()) ? this.url : new webserver.Url("/");
    }

    /**
     * @return Returns the request header. Never returns null. All keys must be
     * lower case.
     */
    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    /**
     * @return Returns the number of header or 0, if no header where found.
     */
    @Override
    public int getHeaderCount() {
        return headers.size();
    }

    /**
     * @return Returns the user agent from the request header
     */
    @Override
    public String getUserAgent() {
        if (this.headers.containsKey("user-agent")) return this.headers.get("user-agent");
        return null;
    }

    /**
     * @return Returns the parsed content length request header. Never returns
     * null.
     */
    @Override
    public int getContentLength() {
        if (this.headers.containsKey("content-length")) return Integer.parseInt(this.headers.get("content-length"));
        return 0;
    }

    /**
     * @return Returns the parsed content type request header. Never returns
     * null.
     */
    @Override
    public String getContentType() { // Was soll es denn dann returnen, wenn nicht null?!
        if (this.headers.containsKey("content-type")) return this.headers.get("content-type");
        return "";
    }

    // ToDo: content as Stream, String, Bytes

    /**
     * @return Returns the request content (body) stream or null if there is no
     * content stream.
     */
    @Override
    public InputStream getContentStream() {
        return null;
    }

    /**
     * @return Returns the request content (body) as string or null if there is
     * no content.
     */
    @Override
    public String getContentString() {
        return null;
    }

    /**
     * @return Returns the request content (body) as byte[] or null if there is
     * no content.
     */
    @Override
    public byte[] getContentBytes() {
        return new byte[0];
    }
}
