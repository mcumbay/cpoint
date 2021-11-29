package com.dfwcomputech.cpoint.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.dfwcomputech.cpoint.java.jep353.GreetClient;
import com.dfwcomputech.cpoint.java.jep353.GreetServer;

import lombok.extern.slf4j.Slf4j;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class Java13Tests {
	GreetServer server;
	int port =6666;
	
	@BeforeAll
	void startServer() throws IOException {
		log.debug("Before");
		Thread newThread = new Thread(() -> {
			server = new GreetServer();
			try {
				server.start(port);
			} catch (IOException e) {
				log.error("{}",e);
			}
		});
		newThread.start();
	}
	/*
	 * JEP-353 Reimplement the Legacy Socket API
	 */
	@Test
	public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect()
			throws UnknownHostException, IOException {
		GreetClient client = new GreetClient();
		client.startConnection("127.0.0.1", port);
		String response = client.sendMessage("hello server");
		assertEquals("hello client", response);
	}
	
	@AfterAll
	void  stopServer() throws IOException {
		log.debug("After");
		server.stop();
	}
}
