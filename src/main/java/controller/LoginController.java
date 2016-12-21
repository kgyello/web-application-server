package controller;

import java.io.DataOutputStream;
import java.io.IOException;

public class LoginController extends ServiceController {
	public LoginController(String requestPath, DataOutputStream dos) {
		super(requestPath, dos);
	}

	@Override
	public void execute() throws IOException {
	}
}
