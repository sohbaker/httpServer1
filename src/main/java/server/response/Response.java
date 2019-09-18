package server.response;

import server.helper.*;

public class Response {
    private ControlCharacter character = new ControlCharacter();
    private String CRLF = character.CRLF();
    private String statusLine;
    private String headers;
    private String body;

    public Response setStatusLine(StatusCode statusCode) {
        String protocol = Protocol._1_1.getVersion();
        String space = character.space();

        this.statusLine = protocol + space + statusCode.getMessage() + CRLF;
        return this;
    }

    public Response setHeaders(String name, String value) {
        if (name != null && value != null) {
            this.headers = formatHeaders(name, value);
        } else {
            this.headers = "";
        }
        return this;
    }

    public Response setBody(String body) {
        this.body = CRLF + body;
        return this;
    }

    public String toString() {
        return this.statusLine + this.headers + this.body;
    }

    private String formatHeaders(String name, String value) {
        String separator = character.separator();
        StringBuilder headers = new StringBuilder().append(name).append(separator).append(value).append(CRLF);

        return headers.toString();
    }
}