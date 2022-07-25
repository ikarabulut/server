package com.ikarabulut.server;

import org.junit.jupiter.api.Test;

import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ActiveConnectionTest {

    @Test
    void ActiveConnection_Run_ShouldSendPassedSocketToStreamGenerator() {
        StreamGenerator connectionIO = new MockIoStream();
        Socket clientSocket = new Socket();
        ActiveConnection connection = new ActiveConnection(clientSocket, connectionIO);

        connection.run();

        assertTrue(connectionIO.setConnectedSocket(clientSocket));
    }

}
