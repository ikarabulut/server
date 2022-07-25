package com.ikarabulut.server.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    InputStream connectionInputStream;
    Map<String, String> requestInitialLine = new HashMap<>();
    Map<String, String> requestHeaders = new HashMap<>();
    String requestBody;

    public Parser(InputStream connectionInputStream) {
        this.connectionInputStream = connectionInputStream;
    }

    public Request parse() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.connectionInputStream));
        StringBuilder builder = new StringBuilder();
        String[] splitRequest = generateSplitRequest(reader, builder);

        String initialLineAndHeaders = splitRequest[0];
        String[] splitInitialLineAndHeader = initialLineAndHeaders.split("\r\n");
        String initialLine = splitInitialLineAndHeader[0];

        String[] initialLineParts = initialLine.split(" ", 3);
        this.requestInitialLine = parseInitialLine(initialLineParts);

        this.requestHeaders = parseHeaders(splitInitialLineAndHeader);

        this.requestBody = null;
        if (splitRequest.length > 1) {
            this.requestBody = splitRequest[1];
        }

        return new Request(this.requestInitialLine, this.requestHeaders, this.requestBody);
    }

    public Map<String, String> getRequestInitialLine() {
        return this.requestInitialLine;
    }

    public Map<String, String> getRequestHeaders() {
        return this.requestHeaders;
    }

    public String getRequestBody() {
        return this.requestBody;
    }

    private String[] generateSplitRequest(BufferedReader reader, StringBuilder builder) throws IOException {
        int availableBytes = connectionInputStream.available();
        int character;
        int bytesRead = 0;
        while (bytesRead < availableBytes) {
            character = reader.read();
            if (!reader.ready()) {
                builder.append((char) character);
                break;
            }
            builder.append((char) character);
            bytesRead++;
        }
        return builder.toString().split("\r\n\r\n");
    }

    private Map<String, String> parseInitialLine(String[] initialLineParts) {
        requestInitialLine.put("method", initialLineParts[0]);
        requestInitialLine.put("resource", initialLineParts[1]);
        requestInitialLine.put("version", initialLineParts[2]);
        return requestInitialLine;
    }

    private Map<String, String> parseHeaders(String[] splitInitialLineAndHeader) {
        for (int i = 1; i < splitInitialLineAndHeader.length; i++) {
            if (!splitInitialLineAndHeader.equals("")) {
                String[] line = splitInitialLineAndHeader[i].split(":", 2);
                String headerName = line[0];
                String headerValue = line[1];
                this.requestHeaders.put(headerName, headerValue);
            }
        }
        return requestHeaders;
    }

}
