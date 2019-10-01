package uebungen;

import java.io.InputStream;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

public class UEB3 {

	public void helloWorld() {

	}

	public Request getRequest(InputStream inputStream) {
		return new webserver.Request(inputStream);
	}

	public Response getResponse() {
		return new webserver.Response();
	}

	public Plugin getTestPlugin() {
		return new ;
	}
}
