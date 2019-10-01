package uebungen;

import java.io.InputStream;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import BIF.SWE1.interfaces.Url;

public class UEB2 {

	public void helloWorld() {

	}

	public Url getUrl(String s) {
		return new webserver.Url(s);
	}

	public Request getRequest(InputStream inputStream) {
		return new webserver.Request(inputStream);
	}

	public Response getResponse() {
		return new webserver.Response();
	}
}
