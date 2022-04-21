package test;

import compiler.Compiler;
import compiler.DynamicClassLoader;
import compiler.SemanticAnalyser;
import nodes.NodeRoot;
import parser.Parser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("src/main/java/test/test.txt");
            StringBuilder stringBuilder = new StringBuilder(50);
            int symbol;
            while ((symbol = fileReader.read()) != -1) {
                stringBuilder.append((char) symbol);
            }
            Parser parser = new Parser(stringBuilder.toString());
            NodeRoot nodeRoot = parser.parse();
            SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
            semanticAnalyser.checkAndFixASTTree(nodeRoot);
            System.out.println(nodeRoot.toTreeishString());

            System.out.println("================================================================");

            String className = "LCPCompiler";
            String methodName = "execute";
            byte[] bytecode = Compiler.compile("LCPCompiler", methodName, nodeRoot);
            DynamicClassLoader classLoader = new DynamicClassLoader();
            Class<?> lcpMainClass = classLoader.loadClass(className, bytecode);
            Method lcpMainMethod = lcpMainClass.getMethod(methodName);
            lcpMainMethod.invoke(null);

            fileReader.close();

        } catch (IOException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
