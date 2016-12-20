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
	
	@Test
	public void perHeaderGenerate() {
		String[] result = requestHandler.perHeaderGenerate("key: value");
		assertEquals(result[0], "key");
		assertEquals(result[1], "value");
		System.out.println(result[0]);
		System.out.println(result[1]);
	}
}
