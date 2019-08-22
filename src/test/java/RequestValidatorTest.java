import org.junit.*;

import static junit.framework.TestCase.assertTrue;

public class RequestValidatorTest {
    private RequestValidator requestValidator = new RequestValidator();

    @Before
    public void instantiate() {
        requestValidator = new RequestValidator();
    }

    @Test
    public void knowsIsValidMethod() {
        String method = "GET";

        assertTrue(requestValidator.isValidMethod(method));
    }

    @Test
    public void knowsRequestLineIsValid() {
        String requestLine = "GET /simple_get HTTP/1.1";

        assertTrue(requestValidator.isValidRequest("GET", requestLine));
    }
}