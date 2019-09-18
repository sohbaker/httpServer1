package server.request;

import static java.lang.Integer.parseInt;
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
        try {
            return getRequestLine()[SECOND_ELEMENT];
        } catch (Exception ArrayIndexOutOfBoundsException) {
            return "";
        }
    }

    public String host() {
        return getValueByHeader("Host");
    }

    public String body() {
        String contentLength = getValueByHeader("Content-Length");
        String lines[] = getLines();
        if (contentLength != null && parseInt(contentLength) > 0) {
            int lastLine = lines.length - 1;
            return lines[lastLine];
        }
        return null;
    }

    public Request buildRequest() {
        return new Request(httpMethod(), path(), host(), body());
    }

    private String[] getLines() {
        return clientRequest.split(CRLF);
    }

    private String[] getRequestLine() {
        String[] lines = getLines();
        return lines[FIRST_ELEMENT].split(SPACE);
    }

    private String getValueByHeader(String header) {
        String[] lines = getLines();
        String[] splitSingleLine;

        for (String line : lines) {
            if (line.contains(header)) {
                splitSingleLine = line.split(COLON);
                return splitSingleLine[SECOND_ELEMENT];
            }
        }
        return null;
    }
}
