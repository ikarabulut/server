package com.ikarabulut.server.http;

import java.util.Map;

public class Request {
    Map<String, String> initialLine;
    String method;
    String resource;
    String version;
    Map<String, String> headers;
    String body;

    public Request(Map<String, String> initialLine, Map<String, String> headers, String body) {
        this.initialLine = initialLine;
        this.method = initialLine.get("method");
        this.resource = initialLine.get("resource");
        this.version = initialLine.get("version");
        this.headers = headers;
        this.body = body;
    }

    public Map<String, String> getInitialLine() {
        return this.initialLine;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public String getBody() {
        return this.body;
    }
}
