package controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import db.DataBase;
import model.User;
import util.HttpRequestUtils;

public class ListController extends ServiceController implements Controller {
	private Map<String, String> headers;

	public ListController(String requestPath, DataOutputStream dos, Map<String, String> headers) {
		super(requestPath, dos);
		this.headers = headers;
	}

	@Override
	public void execute() throws IOException {
		String cookies = headers.get("Cookie");
		Map<String, String> cookiePairs = HttpRequestUtils.parseCookies(cookies);
		Collection<User> allUsers = DataBase.findAll();
		boolean isLoginUser = Boolean.getBoolean(cookiePairs.get("logined"));
		StringBuilder sb = new StringBuilder("[ user list ]\r\n");
		
		for (User user : allUsers) {
			sb.append("userId : " + user.getUserId() + "\r\n");
		}
		if (!isLoginUser) {
			response302(dos, "/user/login.html", isLoginUser);
		}
		byte[] body = sb.toString().getBytes();
		response200Header(dos, body.length, headers);
		responseBody(dos, body);
	}
}
