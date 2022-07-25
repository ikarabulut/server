package com.ikarabulut.server.listener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public interface Listenable {
    Socket beginListening(ServerSocket serverSocket) throws IOException;
    boolean isListening();
}
