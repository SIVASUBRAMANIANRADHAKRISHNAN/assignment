package com.auzmor;

import com.auzmor.server.WebServer;

public class App {
	public static void main(final String[] args) throws Exception {
		new WebServer()

				// Simple POST request
				.post("/auzmor", (request, response) -> {
					return request.body();
				})

				// Start the server
				.start();
	}
}
