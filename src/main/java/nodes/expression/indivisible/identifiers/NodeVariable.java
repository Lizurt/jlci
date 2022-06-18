package nodes.expression.indivisible.identifiers;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.InputMismatchException;

public class NodeVariable extends NodeIdentifier {
    public NodeVariable(String name) {
        super(name);
    }

    @Override
    public void semanticCheckIfExists() {
        if (getScope().tryGetVariableByName(getName()) != null) {
            return;
        }

        throw new InputMismatchException("Unknown variable: \"" + getName() + "\".");
    }

    @Override
    public void semanticCheckIfNotExists() {
        if (getScope().tryGetLabelByName(getName()) == null) {
            return;
        }

        throw new InputMismatchException("A variable \"" + getName() + "\" is already defined in the scope.");
    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(Opcodes.FLOAD, getScope().tryGetVariableByName(getName()).getIndex());
    }
}
