package com.ikarabulut.server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.*;

class HttpServerTest {
    private int PORT = 5000;
    @Test
    void start_ShouldBoundServerSocketToPassedPort() throws IOException {
        int port = 5000;
        Listenable listener = new MockListener();
        HttpServer server = new HttpServer(port, listener);

        server.start();
        ServerSocket serverSocket = server.getServerSocket();

        assertTrue(serverSocket.isBound(), "isBound returned False when the server should be bound to port 5000");
        assertEquals(port, serverSocket.getLocalPort(), "the servers port should have been 5000 but returned" + serverSocket.getLocalPort());

        serverSocket.close();
    }

    @Test
    void start_ShouldBeListeningForConnections() throws IOException {
        int port = 5000;
        Listenable listener = new MockListener();
        HttpServer server = new HttpServer(port, listener);

        server.start();

        assertTrue(listener.isListening());

        ServerSocket serverSocket = server.getServerSocket();
        serverSocket.close();
    }

    @Test
    void start_ShouldExecuteASocketConnectionInAThread() throws IOException {
        Listenable listener = new MockListener();
        HttpServer server = new HttpServer(PORT, listener);

        server.start();

        int expectedRunningThreads = 1;
        int actualRunningThreads = server.getRunningThreadsCount();
        assertEquals(expectedRunningThreads, actualRunningThreads);

        ServerSocket serverSocket = server.getServerSocket();
        serverSocket.close();
    }

}
