package compiler;

import nodes.NodeRoot;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

public class Compiler {
    public final static int counter = 0;
    public final static int ID_THIS = 0;
    public final static int ID_OUTPUT_STREAM = 1;
    public final static int ID_INPUT_STREAM = 2;
    public final static int ID_LAST_EXPRESSION = 3;
    public final static int ID_SCANNER = 4;

    public static byte[] compile(String className, String methodName, NodeRoot root) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        MethodVisitor methodVisitor;

        classWriter.visit(
                Opcodes.V11,
                Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER,
                className,
                null,
                Object.class.getName().replace('.', '/'),
                null
        );

        methodVisitor = classWriter.visitMethod(
                Opcodes.ACC_PUBLIC,
                "<init>",
                "()V",
                null,
                null
        );
        methodVisitor.visitVarInsn(Opcodes.ALOAD, ID_THIS);
        methodVisitor.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                Object.class.getName().replace('.', '/'),
                "<init>",
                "()V",
                false
        );
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(8, 8);
        methodVisitor.visitEnd();

        methodVisitor = classWriter.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
                methodName,
                "([Ljava/lang/String;)V",
                null,
                null
        );

        methodVisitor.visitFieldInsn(
                Opcodes.GETSTATIC,
                System.class.getName().replace('.', '/'),
                "out",
                "Ljava/io/PrintStream;"
        );
        methodVisitor.visitVarInsn(Opcodes.ASTORE, ID_OUTPUT_STREAM);

        methodVisitor.visitFieldInsn(
                Opcodes.GETSTATIC,
                System.class.getName().replace('.', '/'),
                "in",
                "Ljava/io/InputStream;"
        );
        methodVisitor.visitVarInsn(Opcodes.ASTORE, ID_INPUT_STREAM);

        methodVisitor.visitTypeInsn(Opcodes.NEW, Scanner.class.getName().replace('.', '/'));
        methodVisitor.visitVarInsn(Opcodes.ASTORE, ID_SCANNER);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, ID_SCANNER);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, ID_INPUT_STREAM);
        methodVisitor.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                Scanner.class.getName().replace('.', '/'),
                "<init>",
                "(Ljava/io/InputStream;)V",
                false
        );
        methodVisitor.visitVarInsn(Opcodes.ALOAD, ID_SCANNER);
        methodVisitor.visitFieldInsn(
                Opcodes.GETSTATIC,
                Locale.class.getName().replace('.', '/'),
                "US",
                "Ljava/util/Locale;"
        );
        methodVisitor.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                Scanner.class.getName().replace('.', '/'),
                "useLocale",
                "(Ljava/util/Locale;)Ljava/util/Scanner;",
                false
        );

        root.compile(classWriter, methodVisitor);
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(4096, 4096);
        methodVisitor.visitEnd();
        classWriter.visitEnd();
        return classWriter.toByteArray();
    }
}
