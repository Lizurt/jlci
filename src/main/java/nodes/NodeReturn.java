package nodes;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class NodeReturn extends Node{

    @Override
    public String toString() {
        return "->>";
    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {

    }

    @Override
    public void checkAndFixSemantic() {

    }
}
