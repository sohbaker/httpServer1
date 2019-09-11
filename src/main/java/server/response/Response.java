package server.response;

import server.helper.*;

import java.util.List;

public class Response {
    public String statusLine;
    public String headers;
    public String body;

    public Response setStatusLine(StatusCode statusCode) {
        this.statusLine = protocol + space + statusCode.toString() + CRLF;
        return this;
    }

    public Response setHeaders(List<String> headersList) {
        if (headersList != null) {
            this.headers = formatHeaders(headersList);
        } else {
            this.headers = "";
        }
        return this;
    }

    public Response setBody(String body) {
        this.body = CRLF + body;
        return this;
    }

    private String protocol = Protocol._1_1.getVersion();
    private String space = new ControlCharacter().space();
    private String CRLF = new ControlCharacter().CRLF();

    private String formatHeaders(List<String> headers) {
        StringBuilder string = new StringBuilder();
        for (String header: headers) {
            string.append(header).append(CRLF);
        }
        return string.toString();
    }
}