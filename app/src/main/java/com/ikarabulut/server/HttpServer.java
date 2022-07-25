package com.ikarabulut.server;

import java.io.IOException;
import java.net.BindException;
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
    int runningThreads = 0;

    public HttpServer(int port, Listenable listener) {
        this.port = port;
        this.listener = listener;
    }

    public void start() {
        this.threadPool = Executors.newFixedThreadPool(MAX_THREADS);
        try (ServerSocket serverSocket = new ServerSocket(this.port);
             Socket connectedSocket = listener.beginListening(serverSocket)) {
            this.serverSocket = serverSocket;
            StreamGenerator connectionIO = new IoStream();
            threadPool.execute(new ActiveConnection(connectedSocket, connectionIO));
            this.runningThreads += 1;
        } catch (BindException ex) {
            System.err.println("Unable to bind to port: " + this.port + " please try again later");
            System.exit(1);
        } catch (IOException ex) {
            System.err.println("Unable to listen for incoming connections");
            System.exit(1);
        }
    }

    public ServerSocket getServerSocket() {
        return this.serverSocket;
    }

    public int getRunningThreadsCount() {
        return runningThreads;
    }

}
