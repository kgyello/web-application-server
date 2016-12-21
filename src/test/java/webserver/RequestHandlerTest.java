package webserver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.net.Socket;

import org.junit.Before;
import org.junit.Test;

public class RequestHandlerTest {
	Socket mockSocket;
	RequestHandler requestHandler;
	
	@Before
	public void setup() {
		mockSocket = mock(Socket.class);
		requestHandler = new RequestHandler(mockSocket);
	}
}
