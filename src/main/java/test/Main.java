package test;

import compiler.SemanticAnalyser;
import nodes.NodeRoot;
import parser.Parser;

import java.io.FileReader;
import java.io.IOException;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
