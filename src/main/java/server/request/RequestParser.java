package server.request;

import static server.constants.FormatHelpers.*;

public class RequestParser {
    private String clientRequest;
    private static final int FIRST_ELEMENT = 0;
    private static final int SECOND_ELEMENT = 1;

    public RequestParser(String clientRequest) {
        this.clientRequest = clientRequest;
    }

    public String httpMethod() {
        return getRequestLine()[FIRST_ELEMENT];
    }

    public String path() {
        return getRequestLine()[SECOND_ELEMENT];
    }

    public String host() {
        String[] lines = clientRequest.split(CRLF);
        String[] hostLine = new String[2];
        for(String line : lines) {
            if(line.contains("Host")) {
                hostLine = line.split(COLON);
            }
        }
        return hostLine[SECOND_ELEMENT];
    }

    public String body() {
        String[] headerBody = splitAtBlankLine();
        if (splitAtBlankLine().length >1) {
            return splitAtBlankLine()[SECOND_ELEMENT];
        }
        return "";
    }

    public Request buildRequest() {
        return new Request(httpMethod(), path(), host(), body());
    }

    private String[] splitAtBlankLine() {
       return clientRequest.split(CRLF+CRLF);
    }

    private String[] getRequestLine() {
        String[] lines = clientRequest.split(CRLF);
        return lines[FIRST_ELEMENT].split(SPACE);
    }
}
