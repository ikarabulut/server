package com.ikarabulut.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MockListener implements Listenable {
    private boolean serverSocketIsListening;

    @Override
    public Socket beginListening(ServerSocket serverSocket) throws IOException {
        this.serverSocketIsListening = true;
        return null;
    }

    @Override
    public boolean isListening() {
        return serverSocketIsListening;
    }

}
