package compiler;

import nodes.Node;
import nodes.NodeRoot;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Compiler {
    public final static int counter = 0;
    public final static int ID_SELF = 0;
    public final static int ID_OUTPUT_STREAM = 1;
    public final static int ID_INPUT_STREAM = 2;
    public final static int ID_LAST_EXPRESSION = 3;

    public static byte[] compile(String className, NodeRoot root) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        // hi im public class called className working on version 1.8 and extendning java Object
        classWriter.visit(
                Opcodes.V1_8,
                Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER,
                className,
                null,
                Object.class.getName().replace('.', '/'),
                null
        );

        // initialization
        MethodVisitor initMethodVisitor = classWriter.visitMethod(
                Opcodes.ACC_PUBLIC,
                "<init>",
                "()V",
                null,
                null
        );
        initMethodVisitor.visitVarInsn(Opcodes.ALOAD, ID_SELF);
        initMethodVisitor.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                Object.class.getName().replace('.', '/'),
                "<init>",
                "()V",
                false
        );
        initMethodVisitor.visitInsn(Opcodes.RETURN);
        initMethodVisitor.visitEnd();

        // main logic
        MethodVisitor mainMethodVisitor = classWriter.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
                "execute",
                "()V",
                null,
                null
        );
        mainMethodVisitor.visitFieldInsn(
                Opcodes.GETSTATIC,
                "java/lang/System",
                "out",
                "Ljava/io/PrintStream;"
        );
        mainMethodVisitor.visitVarInsn(Opcodes.ASTORE, ID_OUTPUT_STREAM);
        mainMethodVisitor.visitFieldInsn(
                Opcodes.GETSTATIC,
                "java/lang/System",
                "in",
                "Ljava/io/InputStream;"
        );
        mainMethodVisitor.visitVarInsn(Opcodes.ASTORE, ID_INPUT_STREAM);

        for (Node statement : root.getChildes()) {
            statement.compile(classWriter, mainMethodVisitor);
        }

        mainMethodVisitor.visitInsn(Opcodes.RETURN);
        mainMethodVisitor.visitEnd();
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
}
