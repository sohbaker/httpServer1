package server.response;

import server.constants.*;

import static server.constants.FormatHelpers.*;

public class Response {
    private String statusLine;
    private String headers;
    private String body;

    public Response setStatusLine(StatusCode statusCode) {
        String protocol = Protocol._1_1.getVersion();
        this.statusLine = protocol + SPACE + statusCode.getMessage() + CRLF;
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
        if (body != null) {
            this.body = CRLF + body;
        } else {
            this.body = CRLF;
        }
        return this;
    }

    public String toString() {
        return this.statusLine + this.headers + this.body;
    }

    private String formatHeaders(String name, String value) {
        return new StringBuilder().append(name).append(COLON).append(value).append(CRLF).toString();
    }
}