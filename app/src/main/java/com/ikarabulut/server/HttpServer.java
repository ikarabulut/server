package com.ikarabulut.server;

import java.io.IOException;
import java.net.ServerSocket;

public class HttpServer {
    int port;
    ServerSocket serverSocket;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            this.serverSocket = new ServerSocket(this.port);
        } catch (IOException ex) {
            System.err.println("Unable to bind to port: " + this.port + " please try again later");
            System.exit(1);
        }
    }

    public ServerSocket getServerSocket() {
        return this.serverSocket;
    }
}
