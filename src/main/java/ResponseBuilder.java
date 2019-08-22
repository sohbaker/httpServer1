public class ResponseBuilder {
    private String statusLine;
    private String body;
    private String space = new ControlCharacter().space();

    public ResponseBuilder setStatusLine(String protocol, String statusCode, String CRLF) {
        this.statusLine = protocol + space + statusCode + CRLF + CRLF;
        return this;
    }

    public ResponseBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public StringBuilder build() {
        return new StringBuilder().append(this.statusLine).append(this.body);
    }
}