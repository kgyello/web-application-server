package webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import util.HttpRequestUtils;
import util.HttpRequestUtils.Pair;

public class FrontController implements Controller {
	private BufferedReader br;
	private DataOutputStream dos;
	private Map<String, String> headers = new HashMap<>();
	
	public FrontController(BufferedReader br, DataOutputStream dos) {
		this.br = br;
		this.dos = dos;
	}
	
	@Override
	public void execute() throws IOException {
		String requestLine = br.readLine();
		headers = generateHeaders(br);
		Controller controller = RequestDispatcher.disptach(requestLine, headers, dos);
		if (controller == null) {
			return;
		}
		controller.execute();
	}
	
	private Map<String, String> generateHeaders(BufferedReader br) throws IOException {
		String header;
		Map<String, String> headers = Maps.newHashMap();
		
		while (!"".equals((header = br.readLine()))) {
			if (header == null) {
				return headers;
			}
			Pair pair = HttpRequestUtils.parseHeader(header);
			headers.put(pair.getKey(), pair.getValue());
		}
		
		return headers;
	}
}