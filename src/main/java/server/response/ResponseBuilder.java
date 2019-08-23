package server.response;

import server.helper.ControlCharacter;

import java.util.List;

public class ResponseBuilder {
    private String statusLine;
    private String headers;
    private String body;
    private String space = new ControlCharacter().space();
    private String CRLF = new ControlCharacter().CRLF();

    public ResponseBuilder setStatusLine(String protocol, String statusCode) {
        this.statusLine = protocol + space + statusCode + CRLF;
        return this;
    }

    public ResponseBuilder setHeaders(List<String> headers) {
        this.headers = formatHeaders(headers);
        return this;
    }

    public ResponseBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public StringBuilder build() {
        return new StringBuilder().append(this.statusLine).append(this.headers).append(this.body);
    }

    private String formatHeaders(List<String> headers) {
        StringBuilder string = new StringBuilder();
        for (String header: headers) {
            string.append(header).append(CRLF);
        }

        return string.append(CRLF).toString();
    }
}