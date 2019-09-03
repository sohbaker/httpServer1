import org.junit.*;
import server.request.RequestValidator;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class RequestValidatorTest {
    private RequestValidator requestValidator = new RequestValidator();

    @Before
    public void instantiate() {
        requestValidator = new RequestValidator();
    }

    @Test
    public void knowsRequestLineIsValid() {
        String requestLine = "GET /simple_get HTTP/1.1";

        assertTrue(requestValidator.isValidRequest("GET", requestLine));
    }

    @Test
    public void knowsRequestLineIsNotValid() {
        String requestLine = "PUT /fake_test HTTP/1.1";

        assertFalse(requestValidator.isValidRequest("PUT", requestLine));
    }
}