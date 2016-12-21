package webserver;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;

public class ShowController extends ServiceController {
	public ShowController(String requestPath, DataOutputStream dos) {
		super(requestPath, dos);
	}
	
	@Override
	public void execute() throws IOException {
		byte[] body = Files.readAllBytes(new File("./webapp" + requestPath).toPath());
        response200Header(dos, body.length);
        responseBody(dos, body);
	}
}
