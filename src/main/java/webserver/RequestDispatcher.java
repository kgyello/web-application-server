package webserver;

import java.util.HashMap;
import java.util.Map;

public class RequestDispatcher {
	private static final Map<String, Controller> requestMap = new HashMap<>();

	static {
		requestMap.put("/index.html", new ShowController());
		requestMap.put("/user/create", new SignUpController());
	}

	public Controller disptach(String url) {
		return requestMap.get(url);
	}
}
