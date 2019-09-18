package server.request;

import static server.constants.FormatHelpers.*;

public class Request {
//    private String[] headerBodyStrings;
//    private String method;
//    private String path;
//    private String host;

//    public Request extractDetails(String clientRequest) {
//        headerBodyStrings = splitRequestIntoHeaderAndBody(clientRequest);
//        String[] headerAsLines = splitHeaderIntoLines(headerBodyStrings[0]);
//        splitFirstLineOfHeader(headerAsLines[0]);
//        return this;
//    }
//
//    private void setMethod(String method) {
//        this.method = method;
//    }
//
//    private void setPath(String path){
//        this.path = path;
//    }
//
//    private void setHost(String[] requestHeaders) {
//        for(String header : requestHeaders) {
//            if(header.contains("Host")) {
//                String[] extractHost = header.split(": ");
//                this.host = (extractHost[1]);
//            }
//        }
//    }
    private String method;
    private String path;
    private String host;
    private String body;

    public Request(String method, String path, String host, String body){
        this.method = method;
        this.path = path;
        this.host = host;
        this.body = body;
    }

    public String getMethod() {
        return this.method;
    }

    public String getPath() {
        return this.path;
    }

    public String getHost() {
        return this.host;
    }

    public String getBody() {
        return this.body;
    }
}