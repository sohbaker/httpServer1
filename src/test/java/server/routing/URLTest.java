package server.routing;

import org.junit.Test;

import static org.junit.Assert.*;

public class URLTest {
    @Test
    public void buildsAURL() {
        String scheme = "http";
        String host = "host:1000";
        String path = "/the_path";
        URL url = new URL(scheme, host, path);

        assertEquals("http://host:1000/the_path", url.build());
    }
}