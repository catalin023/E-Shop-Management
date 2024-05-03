package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class FileManagement {
    public static void scriereFisierChar(String fileName, String row) {

        DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (FileWriter fw = new FileWriter(fileName, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(row);
            bw.write(";");
            bw.write(TIMESTAMP_FORMATTER.format(LocalDateTime.now()) + "" );
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
