import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        HttpServer server = new HttpServer(new PrintWriter(System.out, true));
        server.start();
    }
}