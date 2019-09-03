package server.request;

import server.helper.ControlCharacter;

public class Request {
    private String clientRequest;
    private String CRLF = new ControlCharacter().CRLF();
    private String headerLine;

    public Request(String clientRequest) {
        this.clientRequest = clientRequest;
    }

    public String getFirstLine() {
        return headerLine;
    }
    
    public String getBody() {
            String body;
            if (hasBody()) {
                body = splitRequestIntoHeaderAndBody()[1];
            } else {
                body = "";
            }
            return body;
    }

    public void extractDetails() {
        String[] headerBody = splitRequestIntoHeaderAndBody();
        String[] headerAsLines = splitHeaderIntoLines(headerBody[0]);
        this.headerLine = headerAsLines[0];
    }

    private String[] splitRequestIntoHeaderAndBody() {
        return clientRequest.split(CRLF + CRLF);
    }

    private boolean hasBody() {
        return splitRequestIntoHeaderAndBody().length > 1;
    }

    private String[] splitHeaderIntoLines(String header) {
        return header.split(CRLF);
    }
}
