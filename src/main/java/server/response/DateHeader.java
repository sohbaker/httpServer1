package server.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHeader {
    public String getDate() {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLL-yyyy HH:mm:ss");

        return now.format(formatter);
    }
}