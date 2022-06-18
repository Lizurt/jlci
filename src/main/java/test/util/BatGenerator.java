package test.util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class BatGenerator {
    public static void main(String[] args) {
        generateBatScript();
    }

    private static void generateBatScript() {
        String baseDirPath = Paths.get("").toAbsolutePath().toString();
        String srcDirPath = baseDirPath + "\\src\\main\\java\\test\\";

        StringBuilder sb = new StringBuilder();
        appendCommand("@echo off", sb);
        appendCommand("%JAVA_HOME%\\bin\\java -jar \"%~dp0jlci.jar%*\"", sb);

        try {
            FileWriter fw = new FileWriter(srcDirPath + "jlci.bat");
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void appendCommand(String command, StringBuilder sb) {
        sb.append(command);
        sb.append("\n\r");
    }
}
