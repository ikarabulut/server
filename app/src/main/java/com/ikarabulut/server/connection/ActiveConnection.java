package com.ikarabulut.server.connection;

import com.ikarabulut.server.http.Parser;
import com.ikarabulut.server.http.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ActiveConnection implements Runnable {
    Socket connectedSocket;
    StreamGenerator connectionIO;
    InputStream connectionInputStream;
    OutputStream connectionOutputStream;
    Request connectionRequest;

    public ActiveConnection(Socket connectedSocket, StreamGenerator connectionIO) {
        this.connectedSocket = connectedSocket;
        this.connectionIO = connectionIO;
    }

    @Override
    public void run() {
        setupConnectionIOStream();
        // TODO:: Once an internal server error response is generateable, I can create a local private method that generates a Request object that is constructed to trigger whatever activates that internal server error Response builder.
        // this.connectionRequest = ((connectionRequest = generateConnectionRequest()) != null) ? connectionRequest : null;
        connectionRequest = generateConnectionRequest();


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

    private Request generateConnectionRequest() {
        try {
            Parser parser = new Parser(this.connectionInputStream);
            this.connectionRequest = parser.parse();
        } catch (IOException ex) {
            System.err.println("System error: Unable to parse input stream, thread will be interrupted.");
            Thread currentThread = Thread.currentThread();
            currentThread.interrupt();
        }
        return connectionRequest;
    }

}
