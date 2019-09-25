package webserver;

import java.util.HashMap;
import java.util.Map;

public class Url implements BIF.SWE1.interfaces.Url {

    private String url = "";
    private String path = "";
    private String fragment = "";
    private Map<String, String> params = new HashMap<>();

    public Url(String url) {
        if (url != null) this.url = url;

        String[] urlSplitFragment = this.url.split("#", 2);
        if (urlSplitFragment.length > 1) {
            this.fragment = urlSplitFragment[1];
        }

        String[] urlSplitParams = urlSplitFragment[0].split("\\?", 2);

        if (urlSplitParams[0] != null) this.path = urlSplitParams[0];

        if (urlSplitParams.length > 1) {
            String paramString = urlSplitParams[1];
            String[] paramArray = paramString.split("&");
            for (String param : paramArray) {
                String[] paramPair = param.split("=", 2);
                this.params.put(paramPair[0], paramPair[1]);
            }
        }
    }

    /**
     * @return Returns the raw url.
     */
    @Override
    public String getRawUrl() {
        return this.url;
    }

    /**
     * @return Returns the path of the url, without parameter.
     */
    @Override
    public String getPath() {
        return this.path;
    }

    /**
     * @return Returns a dictionary with the parameter of the url. Never returns
     * null.
     */
    @Override
    public Map<String, String> getParameter() {
        return this.params;
    }

    /**
     * @return Returns the number of parameter of the url. Returns 0 if there are no parameter.
     */
    @Override
    public int getParameterCount() {
        if (this.params.isEmpty()) {
            return 0;
        }
        return this.params.size();
    }

    /**
     * @return Returns the segments of the url path. A segment is divided by '/'
     * chars. Never returns null.
     */
    @Override
    public String[] getSegments() {
        String trimmedPath = this.path.replaceAll("^/+","");
        String[] segments = trimmedPath.split("/");
        if (segments.length <= 0 || segments.length == 1 && segments[0].equals("")) return new String[] {};
        return segments;
    }

    /**
     * @return Returns the filename (with extension) of the url path. If the url
     * contains no filename, a empty string is returned. Never returns
     * null. A filename is present in the url, if the last segment
     * contains a name with at least one dot.
     */
    @Override
    public String getFileName() {
        String[] segments = this.getSegments();
        if (segments.length > 0 && segments[segments.length - 1].contains(".")) return segments[segments.length - 1];
        return "";
    }

    /**
     * @return Returns the extension of the url filename, including the leading
     * dot. If the url contains no filename, a empty string is returned.
     * Never returns null.
     */
    @Override
    public String getExtension() {
        String filename = this.getFileName();
        if (filename.isEmpty()) return "";
        String[] split = filename.split("\\.");
        return "." + split[split.length - 1];
    }

    /**
     * @return Returns the url fragment. A fragment is the part after a '#' char
     * at the end of the url. If the url contains no fragment, a empty
     * string is returned. Never returns null.
     */
    @Override
    public String getFragment() {
        return this.fragment;
    }
}
