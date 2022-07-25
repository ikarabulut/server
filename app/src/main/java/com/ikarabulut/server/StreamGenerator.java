package com.ikarabulut.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface StreamGenerator {
    InputStream generateInputStream() throws IOException;
    OutputStream generateOutputStream() throws IOException;
    boolean setConnectedSocket(Socket connectedSocket);

}
