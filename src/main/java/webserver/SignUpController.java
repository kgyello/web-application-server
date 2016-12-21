package webserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import db.DataBase;
import model.User;
import util.HttpRequestUtils;

public class SignUpController extends ServiceController {
	private String httpMethod;
	private String queryString;
	private Map<String, String> headers;
	
	public SignUpController(String requestPath, DataOutputStream dos, String httpMethod, String queryString, Map<String, String> headers) {
		super(requestPath, dos);
		this.httpMethod = httpMethod;
		this.queryString = queryString;
		this.headers = headers;
	}

	@Override
	public void execute() throws IOException {
		if ("GET".equals(httpMethod)) {
			addUser(createGetUser(queryString));
		}
		return;
	}
	
	private User createGetUser(String queryString) {
		Map<String, String> params = HttpRequestUtils.parseQueryString(queryString);
		return new User(params.get("userId"), params.get("password"), params.get("name"), params.get("email"));
	}
	
//	private User createPostUser(String queryString) {
//	}
	
	private void addUser(User user) {
		DataBase.addUser(user);
	}
}
