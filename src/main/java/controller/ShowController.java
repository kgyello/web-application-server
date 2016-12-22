package controller;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class ShowController extends ServiceController {
	private Map<String, String> headers;
	
	public ShowController(String requestPath, DataOutputStream dos, Map<String, String> headers) {
		super(requestPath, dos);
		this.headers = headers;
	}
	
	@Override
	public void execute() throws IOException {
		byte[] body = Files.readAllBytes(new File("./webapp" + requestPath).toPath());
        response200Header(dos, body.length, headers);
        responseBody(dos, body);
	}
}
