package controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

import db.DataBase;
import model.User;
import util.HttpRequestUtils;

public class SignUpController extends ServiceController {
	private String httpMethod;
	private String queryString;
	private String body;
	
	public SignUpController(String requestPath, DataOutputStream dos, String httpMethod, String queryString, String body) {
		super(requestPath, dos);
		this.httpMethod = httpMethod;
		this.queryString = queryString;
		this.body = body;
	}

	@Override
	public void execute() throws IOException {
		if ("GET".equals(httpMethod)) {
			addUser(createUser(queryString));
			return;
		}
		addUser(createUser(body));
		response302(dos, "/index.html", false);
	}
	
	private User createUser(String queryString) {
		Map<String, String> params = HttpRequestUtils.parseQueryString(queryString);
		return new User(params.get("userId"), params.get("password"), params.get("name"), params.get("email"));
	}
	
	private void addUser(User user) {
		DataBase.addUser(user);
	}
}
