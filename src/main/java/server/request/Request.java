package server.request;

import server.helper.ControlCharacter;

public class Request {
    private String CRLF = new ControlCharacter().CRLF();
    private String space = new ControlCharacter().space();
    private String[] headerBody;
    private String method;
    private String path;

    public Request extractDetails(String clientRequest) {
        headerBody = splitRequestIntoHeaderAndBody(clientRequest);
        String[] headerAsLines = splitHeaderIntoLines(headerBody[0]);
        splitFirstLineOfHeader(headerAsLines[0]);
        return this;
    }

    public String getMethod() {
        return this.method;
    }

    public String getPath() {
        return this.path;
    }

    public String getBody() {
            String body;
            if (hasBody()) {
                body = headerBody[1];
            } else {
                body = "";
            }
            return body;
    }

    private String[] splitRequestIntoHeaderAndBody(String clientRequest) {
        return clientRequest.split(CRLF + CRLF);
    }

    private String[] splitHeaderIntoLines(String header) {
        return header.split(CRLF);
    }

    private void splitFirstLineOfHeader(String firstLine) {
        String[] splitMethodPath = firstLine.split(space);
        this.method = splitMethodPath[0];
        this.path = splitMethodPath[1];
    }

    private boolean hasBody() {
        return headerBody.length > 1;
    }
}