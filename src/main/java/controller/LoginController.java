package controller;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import db.DataBase;
import model.User;
import util.HttpRequestUtils;

public class LoginController extends ServiceController {
	private String body;
	private Map<String, String> headers;

	public LoginController(String requestPath, DataOutputStream dos, String body, Map<String, String> headers) {
		super(requestPath, dos);
		this.body = body;
		this.headers = headers;
	}

	@Override
	public void execute() throws IOException {
		Map<String, String> params = HttpRequestUtils.parseQueryString(body);
		String path = "/index.html";
		boolean cookieFlag = true;
		if (!authUser(params.get("userId"), params.get("password"))) {
			path = "/user/login_failed.html";
			cookieFlag = false;
		}
		response302(dos, path, cookieFlag);
	}

	private boolean authUser(String userId, String password) {
		User user = DataBase.findUserById(userId);
		if (user == null) {
			return false;
		}

		if (user.getUserId().equals(userId) && user.getPassword().equals(password)) {
			return true;
		}

		return false;
	}
}
