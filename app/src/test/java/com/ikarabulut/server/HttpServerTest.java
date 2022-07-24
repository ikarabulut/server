package com.ikarabulut.server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.*;

class HttpServerTest {

    @Test
    void runningServerShouldBeBoundToPassedPort() throws IOException {
        int port = 5000;
        HttpServer server = new HttpServer(port);

        server.start();
        ServerSocket serverSocket = server.getServerSocket();

        assertTrue(serverSocket.isBound(), "isBound returned False when the server should be bound to port 5000");
        assertEquals(port, serverSocket.getLocalPort(), "the servers port should have been 5000 but returned" + serverSocket.getLocalPort());

        serverSocket.close();
    }

}
