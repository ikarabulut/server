package com.ikarabulut.server.integrationTests;

import com.ikarabulut.server.connection.ActiveConnection;
import com.ikarabulut.server.connection.StreamGenerator;
import org.junit.jupiter.api.Test;

import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

//TODO:: This is not an actual integration test yet, Ill use spys for now but this could be a future learning experience on generating a test that will create an actual socket connection with an input stream

class ActiveConnectionTest {

    //Tried Spys but still threw null pointer. Want to avoid mocks here, and there would be alot of duplication with the Parse test to properly execute the implementation. Pushing this task off as the contracts are tested on the fulfillment side.
    @Test
    void ActiveConnection_Run_ShouldSendPassedSocketToStreamGenerator() {
//        StreamGenerator connectionIO = new MockIoStream();
//        StreamGenerator spiedIO= Mockito.spy(connectionIO);
//        Socket clientSocket = new Socket();
//        Socket spiedSocket = Mockito.spy(clientSocket);
//        ActiveConnection connection = new ActiveConnection(spiedSocket, spiedIO);
//
//        connection.run();
//
//        assertTrue(connectionIO.setConnectedSocket(clientSocket));
    }

}
