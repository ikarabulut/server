package com.ikarabulut.server.unitTests;

import com.ikarabulut.server.http.Parser;
import com.ikarabulut.server.http.Request;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private final String input = "HEAD /test HTTP/1.1\r\n" +
            "Content-Type" + ":" + "text/plain\r\n" +
            "User-Agent" + ":" + "PostmanRuntime/7.29.0\r\n\r\n" +
            "Hello World";

    private final String inputWithoutBody = "HEAD /test HTTP/1.1 \r\n" +
            "Content-Type" + ":" + "text/plain\r\n" +
            "User-Agent" + ":" + "PostmanRuntime/7.29.0\r\n\r\n";

    @Test
    void parse_ShouldSetInputLine_ToEqualInputStreamsInitialLine() throws IOException {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Parser parser = new Parser(in);
        Map<String, String> expectedInitialLine = new HashMap<>();
        expectedInitialLine.put("method", "HEAD");
        expectedInitialLine.put("resource", "/test");
        expectedInitialLine.put("version", "HTTP/1.1");

        parser.parse();
        Map<String, String> parsedInitialLine = parser.getRequestInitialLine();

        assertEquals(expectedInitialLine, parsedInitialLine);

        in.close();
    }

    @Test
    void parse_ShouldSetHeaders_ToEqualInputStreamsHeaders() throws IOException {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Parser parser = new Parser(in);
        Map<String, String> expectedHeaders = new HashMap<>();
        expectedHeaders.put("Content-Type", "text/plain");
        expectedHeaders.put("User-Agent", "PostmanRuntime/7.29.0");

        parser.parse();
        Map<String, String> parsedHeaders = parser.getRequestHeaders();

        assertEquals(expectedHeaders, parsedHeaders);

        in.close();
    }

    @Test
    void parse_ShouldSetBody_ToEqualInputStreamsBody_IfBodyExists() throws IOException {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Parser parser = new Parser(in);
        String expectedParsedBody = "Hello World";

        parser.parse();
        String parsedBody = parser.getRequestBody();

        assertEquals(expectedParsedBody, parsedBody);

        in.close();
    }

    @Test
    void parse_ShouldSetBody_ToNull_IfNoBodyExists() throws IOException {
        InputStream in = new ByteArrayInputStream(inputWithoutBody.getBytes());
        Parser parser = new Parser(in);

        parser.parse();
        String parsedBody = parser.getRequestBody();

        assertNull(parsedBody);

        in.close();
    }

    @Test
    void parse_ShouldReturnANewRequestObject_WithTheParsedFields() throws IOException {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Parser parser = new Parser(in);

        Request parsedRequestObject = parser.parse();

        assertEquals(parser.getRequestInitialLine(), parsedRequestObject.getInitialLine());
        assertEquals(parser.getRequestHeaders(), parsedRequestObject.getHeaders());
        assertEquals(parser.getRequestBody(), parsedRequestObject.getBody());


        in.close();
    }

}
