package com.ikarabulut.server.unitTests;

import com.ikarabulut.server.connection.ActiveConnection;
import com.ikarabulut.server.connection.StreamGenerator;
import com.ikarabulut.server.mocks.MockIoStream;
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
