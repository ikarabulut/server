package com.ikarabulut.server;

import java.net.ServerSocket;
import java.net.Socket;

public class MockListener implements Listenable {
    private boolean serverSocketIsListening;

    @Override
    public Socket beginListening(ServerSocket serverSocket) {
        this.serverSocketIsListening = true;
        return new Socket();
    }

    @Override
    public boolean isListening() {
        return serverSocketIsListening;
    }

}
