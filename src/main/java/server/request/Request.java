package server.request;

import server.helper.ControlCharacter;

public class Request {
    private String CRLF = new ControlCharacter().CRLF();
    private String space = new ControlCharacter().space();
    private String[] headerBody;
    private String method;
    private String path;
    private String host;

    public Request extractDetails(String clientRequest) {
        headerBody = splitRequestIntoHeaderAndBody(clientRequest);
        String[] headerAsLines = splitHeaderIntoLines(headerBody[0]);
        splitFirstLineOfHeader(headerAsLines[0]);
        return this;
    }

    private void setHost(String[] requestHeaders) {
        for(String header : requestHeaders) {
            if(header.contains("Host")) {
                String[] extractHost = header.split(": ");
                this.host = (extractHost[1]);
            }
        }
    }

    private void setMethod(String method) {
        this.method = method;
    }

    private void setPath(String path){
        this.path = path;
    }

    public String getHost() {
        return this.host;
    }

    public String getMethod() {
        return this.method;
    }

    public String getPath() {
        return this.path;
    }

    public String getBody() {
        if (hasBody()) {
            return headerBody[1];
        }
        return "";
    }

    private String[] splitRequestIntoHeaderAndBody(String clientRequest) {
        return clientRequest.split(CRLF + CRLF);
    }

    private String[] splitHeaderIntoLines(String header) {
        String[] headerAsLines = header.split(CRLF);
        setHost(headerAsLines);
        return header.split(CRLF);
    }

    private void splitFirstLineOfHeader(String firstLine) {
        String[] splitMethodPath = firstLine.split(space);
        setMethod(splitMethodPath[0]);
        setPath(splitMethodPath[1]);
    }

    private boolean hasBody() {
        return headerBody.length > 1;
    }
}