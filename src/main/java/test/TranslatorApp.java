package test;

import translator.cplusparser.CPlusParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TranslatorApp {
    public static void main(String[] args) {
        testTranslator();
//        try {
//            FileReader fileReader = new FileReader("src/main/java/test/test.txt");
//            StringBuilder stringBuilder = new StringBuilder(50);
//            int symbol;
//            while ((symbol = fileReader.read()) != -1) {
//                stringBuilder.append((char) symbol);
//            }
//            Parser parser = new Parser(stringBuilder.toString());
//            NodeRoot nodeRoot = parser.parse();
//            SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
//            semanticAnalyser.checkAndFixASTTree(nodeRoot);
//            System.out.println(nodeRoot.toTreeishString());
//
//            System.out.println("================================================================");
//
//            String className = "LCPCompiler";
//            String methodName = "execute";
//            byte[] bytecode = Compiler.compile("LCPCompiler", methodName, nodeRoot);
//            DynamicClassLoader classLoader = new DynamicClassLoader();
//            Class<?> lcpMainClass = classLoader.loadClass(className, bytecode);
//            Method lcpMainMethod = lcpMainClass.getMethod(methodName);
//            lcpMainMethod.invoke(null);
//
//            fileReader.close();
//
//        } catch (IOException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }

    public static void testTranslator() {
        try {
            FileReader fileReader = new FileReader("src/main/java/test/test_c.txt");
            StringBuilder stringBuilder = new StringBuilder(100);
            int symbol;
            while ((symbol = fileReader.read()) != -1) {
                stringBuilder.append((char) symbol);
            }
            CPlusParser cPlusParser = new CPlusParser(stringBuilder.toString());
            String s = cPlusParser.parse().toTreeishString();
            System.out.println(s);

            FileWriter writer = new FileWriter("src/main/java/test/test_c_result.txt", false);
            writer.write(s);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
