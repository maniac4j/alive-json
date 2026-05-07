package uz.maniac4j.examples;

import uz.maniac4j.alivejson.Json;
import uz.maniac4j.alivejson.io.Parsed;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReadingExample {
    public static void main(String[] args) throws Exception {
        // Read file content into a string
        Path path = Paths.get("data.json");
        String content = Files.readString(path);

        // Pass to the lazy parser
        Json fileJson = new Parsed(content);

        // Safely extract data
        System.out.println(fileJson.value("settings").value("port").text());
    }
}
