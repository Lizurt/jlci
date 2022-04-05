package test;

import parser.Parser;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("src/test/test.txt");
            StringBuilder stringBuilder = new StringBuilder(50);
            int symbol;
            while ((symbol = fileReader.read()) != -1) {
                stringBuilder.append((char) symbol);
            }
            Parser parser = new Parser(stringBuilder.toString());
            System.out.println(parser.parse().toTreeishString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void test() {
        double a = 3.4d;
        double b = 11d;
        double c = a + b;
        if (c > 50) {
            return;
        }
    }
}
