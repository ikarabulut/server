package com.ikarabulut.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    int port;
    ServerSocket serverSocket;
    Listenable listener;
    ExecutorService threadPool;
    int MAX_THREADS = 10;

    public HttpServer(int port, Listenable listener) {
        this.port = port;
        this.listener = listener;
    }

    public void start() {
        this.threadPool = Executors.newFixedThreadPool(MAX_THREADS);
        try {
            this.serverSocket = new ServerSocket(this.port);
            Socket connectedSocket = listener.beginListening(serverSocket);
        } catch (IOException ex) {
            System.err.println("Unable to bind to port: " + this.port + " please try again later");
            System.exit(1);
        }
    }

    public ServerSocket getServerSocket() {
        return this.serverSocket;
    }

}
