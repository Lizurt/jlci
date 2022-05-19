package test;

import compiler.Compiler;
import compiler.DynamicClassLoader;
import compiler.SemanticAnalyser;
import nodes.NodeRoot;
import parser.Parser;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class StartPoint {
    private static final String MAIN_METHOD_NAME = "main";

    public static void main(String[] args) {
        try {
            String pathToLCPFile = "src/main/java/test/test.txt";
            String compiledLCPFilePath = "src/main/java/test/test.class";
            if (args.length > 0) {
                pathToLCPFile = args[0];
                compiledLCPFilePath = args[0].replace(".txt", ".class");
            }

            FileReader fileReader = new FileReader(pathToLCPFile);
            StringBuilder stringBuilder = new StringBuilder(50);
            int symbol;
            while ((symbol = fileReader.read()) != -1) {
                stringBuilder.append((char) symbol);
            }
            Parser parser = new Parser(stringBuilder.toString());
            NodeRoot nodeRoot = parser.parse();
            SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
            semanticAnalyser.checkAndFixASTTree(nodeRoot);

            if (args.length > 1) {
                if (!args[1].equals("-d")) {
                    System.out.println("Unexpected arg: " + args[1]);
                    return;
                }
                System.out.println(nodeRoot.toTreeishString());
                System.out.println("================================================================");
            }

            String fileName = findFileNameInPath(pathToLCPFile);
            byte[] bytecode = Compiler.compile(fileName, MAIN_METHOD_NAME, nodeRoot);
            OutputStream outputStream = new FileOutputStream(compiledLCPFilePath);
            outputStream.write(bytecode);
            outputStream.close();
            //DynamicClassLoader classLoader = new DynamicClassLoader();
            //Class<?> lcpMainClass = classLoader.loadClass(className, bytecode);
            //Method lcpMainMethod = lcpMainClass.getMethod(methodName);
            //lcpMainMethod.invoke(null);

            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } //catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            //e.printStackTrace();
        //}
    }

    private static String findFileNameInPath(String path) {
        int to = -1;
        for (int i = path.length() - 1; i >= 0; i--) {
            if (path.charAt(i) == '.') {
                to = i;
                continue;
            }
            if (path.charAt(i) == '/') {
                return path.substring(i + 1, to);
            }
        }
        return null;
    }
}
