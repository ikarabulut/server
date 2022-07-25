package com.ikarabulut.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener implements Listenable {
    private boolean serverSocketIsListening;

    @Override
    public Socket beginListening(ServerSocket serverSocket) throws IOException {
        serverSocketIsListening = true;
        return serverSocket.accept();
    }

    public boolean isListening() {
        return serverSocketIsListening;
    }

}
