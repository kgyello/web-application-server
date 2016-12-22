package controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Controller {
	static final Logger log = LoggerFactory.getLogger(Controller.class);
	
	void execute() throws IOException;
	
	default void response200Header(DataOutputStream dos, int lengthOfBodyContent, Map<String, String> headers) {
		try {
			dos.writeBytes("HTTP/1.1 200 OK \r\n");
			dos.writeBytes("Content-Type: " + headers.get("Accept") + "\r\n");
			dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
			dos.writeBytes("\r\n");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
	
	default void response302(DataOutputStream dos, String url, boolean cookieFlag) {
		try {
			dos.writeBytes("HTTP/1.1 302 Found \r\n");
			dos.writeBytes("Location: http://localhost:7070" + url + "\r\n");
			dos.writeBytes("Set-Cookie: logined=" + cookieFlag);
			dos.writeBytes("\r\n");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	default void responseBody(DataOutputStream dos, byte[] body) {
		try {
			dos.write(body, 0, body.length);
			dos.flush();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
