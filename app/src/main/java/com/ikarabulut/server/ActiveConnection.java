package com.ikarabulut.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ActiveConnection implements Runnable {
    Socket connectedSocket;
    StreamGenerator connectionIO;
    InputStream connectionInputStream;
    OutputStream connectionOutputStream;

    public ActiveConnection(Socket connectedSocket, StreamGenerator connectionIO) {
        this.connectedSocket = connectedSocket;
        this.connectionIO = connectionIO;
    }

    @Override
    public void run() {
        setupConnectionIOStream();

    }

    public OutputStream getConnectionOutputStream() {
        return this.connectionOutputStream;
    }

    public InputStream getConnectionInputStream() {
        return this.connectionInputStream;
    }

    private void setupConnectionIOStream() {
        try {
            connectionIO.setConnectedSocket(connectedSocket);
            this.connectionInputStream = connectionIO.generateInputStream();
            this.connectionOutputStream = connectionIO.generateOutputStream();
        } catch (IOException ex) {
            Thread currentThread = Thread.currentThread();
            currentThread.interrupt();
        }
    }

}
