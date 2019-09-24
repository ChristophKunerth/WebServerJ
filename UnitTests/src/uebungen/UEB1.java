package uebungen;

import BIF.SWE1.interfaces.Url;

public class UEB1 {

	public Url getUrl(String path) {
		return new webserver.Url(path);
	}

	public void helloWorld() {
		System.out.print("Hello World!");
	}
}
