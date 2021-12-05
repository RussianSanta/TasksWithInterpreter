package helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class FileHelper {
    public static String getTextFromFile() {
        return getTextFromFile("INPUT.txt");
    }

    public static String getTextFromFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + fileName));
            String text = "";
            String line;
            while ((line = reader.readLine())!=null) {
                text += line;
            }
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void printTextToFile(String result) {
        printTextToFile("OUTPUT.txt",result);
    }

    public static void printTextToFile(String fileName, String result) {
        try {
            PrintWriter writer = new PrintWriter("src/main/resources/" + fileName, StandardCharsets.UTF_8);
            writer.println(result);
            writer.flush();
            writer.close();
            System.out.print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
