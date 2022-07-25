package com.ikarabulut.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IoStream implements StreamGenerator {
    Socket connectedSocket;
    boolean socketHasBeenSet;

    @Override
    public InputStream generateInputStream() throws IOException {
        return connectedSocket.getInputStream();
    }

    @Override
    public OutputStream generateOutputStream() throws IOException {
        return connectedSocket.getOutputStream();
    }

    @Override
    public boolean setConnectedSocket(Socket connectedSocket) {
        this.connectedSocket = connectedSocket;
        return socketHasBeenSet = true;
    }

}
