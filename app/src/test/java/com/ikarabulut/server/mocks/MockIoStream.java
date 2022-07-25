package com.ikarabulut.server.mocks;

import com.ikarabulut.server.connection.StreamGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MockIoStream implements StreamGenerator {
    Socket connectedSocket;
    boolean socketHasBeenSet;

    @Override
    public InputStream generateInputStream() throws IOException {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
    }

    @Override
    public OutputStream generateOutputStream() throws IOException {
        return new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        };
    }

    @Override
    public boolean setConnectedSocket(Socket connectedSocket) {
        this.connectedSocket = connectedSocket;
        return this.socketHasBeenSet = true;
    }


}
