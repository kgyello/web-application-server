package webserver;

import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestDispatcher {
	private static final Logger log = LoggerFactory.getLogger(RequestDispatcher.class);

	public static Controller disptach(String requestLine, Map<String, String> headers, DataOutputStream dos) {
		String[] requestLineTokens = requestLine.split(" ");
		String httpMethod = requestLineTokens[0];
		String url = requestLineTokens[1];
		String requestPath = url;
		String queryString = ""; // 회원가입할때 예외처리해줘야할듯
		int splitIndex = url.indexOf("?");

		if (splitIndex != -1) {
			requestPath = url.substring(0, splitIndex);
			queryString = url.substring(splitIndex + 1);
		}
		
		if ("/user/create".equals(requestPath)) {
			return new SignUpController(requestPath, dos, httpMethod, queryString, headers);
		}

		return new ShowController(requestPath, dos);
	}
}
