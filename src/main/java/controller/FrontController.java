package controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import util.HttpRequestUtils;
import util.HttpRequestUtils.Pair;
import webserver.UrlDispatcher;
import util.IOUtils;

public class FrontController implements Controller {
	private BufferedReader br;
	private DataOutputStream dos;
	private Map<String, String> headers = new HashMap<>();
	private String body;

	public FrontController(BufferedReader br, DataOutputStream dos) {
		this.br = br;
		this.dos = dos;
	}

	@Override
	public void execute() throws IOException {
		String requestLine = br.readLine();
		headers = generateHeaders(br);
		body = generateBody(br);
		
		Controller controller = UrlDispatcher.disptach(requestLine, headers, body, dos);
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
//			log.info("header : {}", header);
			Pair pair = HttpRequestUtils.parseHeader(header);
			headers.put(pair.getKey(), pair.getValue());
		}

		return headers;
	}

	private String generateBody(BufferedReader br) throws IOException {
		String ContentLength = headers.get("Content-Length");
		if (ContentLength == null) {
//			log.info("requestBody is empty");
			return null;
		}
		String body = IOUtils.readData(br, Integer.parseInt(ContentLength));
		log.info("requestBody is {}", body);
		return body;
	}
}