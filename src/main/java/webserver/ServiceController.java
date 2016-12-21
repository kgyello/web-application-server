package webserver;

import java.io.DataOutputStream;

abstract class ServiceController implements Controller {
	protected String requestPath;
	protected DataOutputStream dos;
	
	public ServiceController(String requestPath, DataOutputStream dos) {
		this.requestPath = requestPath;
		this.dos = dos;
	}
}
