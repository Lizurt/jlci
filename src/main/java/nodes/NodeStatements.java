package nodes;

import compiler.Scope;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class NodeStatements extends Node {
    @Override
    public String toString() {
        return "...";
    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {
        for (Node statement : getChildes()) {
            statement.compile(classWriter, methodVisitor);
        }
    }

    @Override
    public void checkAndFixSemantic() {
        Scope scope = new Scope(getScope());
        for (Node statement : getChildes()) {
            statement.setScope(scope);
            statement.checkAndFixSemantic();
        }
    }
}
